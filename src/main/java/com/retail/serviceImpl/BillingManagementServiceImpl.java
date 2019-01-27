package com.retail.serviceImpl;

import com.retail.dao.BillDAO;
import com.retail.dao.ProductDAO;
import com.retail.dao.PurchaseItemDAO;
import com.retail.domain.Bill;
import com.retail.domain.Product;
import com.retail.domain.PurchaseItem;
import com.retail.exception.RetailValidationException;
import com.retail.service.BillingManagementService;
import com.retail.wrapper.BillingResponse;
import com.retail.wrapper.PurchaseItemRequest;
import com.retail.wrapper.PurchaseItemResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * Represents actual implementations of CRUD operations related to Billing
 * Created by Mahesh Maykarkar on 26/01/19.
 */

@Service
public class BillingManagementServiceImpl implements BillingManagementService {

    @Autowired
    ProductDAO productDAO;

    @Autowired
    BillDAO billDAO;

    @Autowired
    PurchaseItemDAO purchaseItemDAO;

    @Transactional
    @Override
    public BillingResponse createBill(List<PurchaseItemRequest> purchaseItemRequestList) {

        Set<PurchaseItem> purchases = new HashSet<>();

        for (PurchaseItemRequest purchaseItemRequest : purchaseItemRequestList) {
            Product product = productDAO.findByBarCodeId(purchaseItemRequest.getBarCodeId());
            PurchaseItem purchaseItem = new PurchaseItem(product, purchaseItemRequest.getQuantity());
            purchases.add(purchaseItem);
        }

        Bill bill = new Bill();
        bill.setPurchaseItems(purchases);

        Bill saveBill = billDAO.save(bill);

        return new BillingResponse(saveBill);
    }

    @Transactional
    @Override
    public BillingResponse getBillingDetails(int billingId) {
        List<PurchaseItem> purchaseItems = purchaseItemDAO.findByBillingId(billingId);

        if (CollectionUtils.isEmpty(purchaseItems)) {
            throw new RetailValidationException("Invalid Billing Id");
        }

        BillingResponse billingResponse = new BillingResponse();
        List<PurchaseItemResponse> purchaseItemList = new ArrayList<>();
        for (PurchaseItem purchaseItem : purchaseItems) {
            billingResponse.setBillingId(purchaseItem.getBill().getId());
            billingResponse.setTotalCost(purchaseItem.getBill().getTotalCost());
            billingResponse.setTotalSalesTax(purchaseItem.getBill().getTotalSalesTax());
            billingResponse.setTotalBillAmount(Math.round((purchaseItem.getBill().getTotalCost() + purchaseItem.getBill().getTotalSalesTax()) * 100.0)/100.0);

            PurchaseItemResponse purchaseItemResponse = new PurchaseItemResponse();
            purchaseItemResponse.setProductName(purchaseItem.getProduct().getName());
            purchaseItemResponse.setSalesTaxPerItem(Math.round((purchaseItem.getPurchaseSalesTax() / purchaseItem.getQuantity())*100.0)/100.0);
            purchaseItemResponse.setPurchasedCost(purchaseItem.getPurchaseCost());
            purchaseItemResponse.setCostPerItem(Math.round((purchaseItem.getPurchaseCost() / purchaseItem.getQuantity())*100.0)/100.0);
            purchaseItemResponse.setPurchasedSalesTax(purchaseItem.getPurchaseSalesTax());
            purchaseItemResponse.setQuantity(purchaseItem.getQuantity());
            purchaseItemList.add(purchaseItemResponse);

        }
        billingResponse.setPurchaseItems(purchaseItemList);
        return billingResponse;
    }
}
