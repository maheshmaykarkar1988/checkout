package com.retail.serviceImpl;

import com.retail.dao.BillDAO;
import com.retail.dao.ProductDAO;
import com.retail.dao.PurchaseItemDAO;
import com.retail.domain.Bill;
import com.retail.domain.Product;
import com.retail.domain.PurchaseItem;
import com.retail.exception.NoRecordFoundException;
import com.retail.exception.RetailValidationException;
import com.retail.service.BillingManagementService;
import com.retail.wrapper.BillingRequest;
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

    /**
     * Creates total bill for all purchased items
     * @param billingRequest
     * @return
     */
    @Transactional
    @Override
    public BillingResponse createBill(BillingRequest billingRequest) {

        Set<PurchaseItem> purchases = new HashSet<>();

        for (PurchaseItemRequest purchaseItemRequest : billingRequest.getPurchaseItems()) {
            Product product = productDAO.findByBarCodeId(purchaseItemRequest.getBarCodeId());
            if (Objects.isNull(product))
                throw new NoRecordFoundException(String.format("Product with Barcode %s doesn't exists in system", purchaseItemRequest.getBarCodeId()));
            PurchaseItem purchaseItem = new PurchaseItem(product, purchaseItemRequest.getQuantity());
            purchases.add(purchaseItem);
        }

        Bill bill = new Bill();
        bill.setPurchaseItems(purchases);

        Bill saveBill = billDAO.save(bill);

        return new BillingResponse(saveBill);
    }

    /**
     * Returns billing details for given billing id
     * @param billingId
     * @return {@link BillingResponse}
     */
    @Transactional
    @Override
    public BillingResponse getBillingDetails(Long billingId) {
        List<PurchaseItem> purchaseItems = purchaseItemDAO.findByBillingId(billingId);

        if (CollectionUtils.isEmpty(purchaseItems)) {
            throw new RetailValidationException("Invalid Billing Id");
        }

        BillingResponse billingResponse = new BillingResponse();
        List<PurchaseItemResponse> purchaseItemList = new ArrayList<>();
        for (PurchaseItem purchaseItem : purchaseItems) {
            billingResponse.setResponseDetails(billingResponse, purchaseItem);
            PurchaseItemResponse purchaseItemResponse = new PurchaseItemResponse(purchaseItem);
            purchaseItemList.add(purchaseItemResponse);
        }

        billingResponse.setPurchaseItems(purchaseItemList);
        return billingResponse;
    }
}
