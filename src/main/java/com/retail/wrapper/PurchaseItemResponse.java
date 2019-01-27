package com.retail.wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.retail.domain.PurchaseItem;
import lombok.Data;

/**
 * Responsible for holding purchase item response
 * Created by Mahesh Maykarkar on 26/01/19.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class PurchaseItemResponse {
    private String productName;
    private double costPerItem;
    private double salesTaxPerItem;
    private float quantity;
    private double purchasedCost;
    private double purchasedSalesTax;

    /**
     * Maps purchase items details so as to send it back for billing
     * @param purchaseItem
     */
    public PurchaseItemResponse(PurchaseItem purchaseItem) {
        this.productName = purchaseItem.getProduct().getName();
        this.salesTaxPerItem = (Math.round((purchaseItem.getPurchaseSalesTax() / purchaseItem.getQuantity()) * 100.0) / 100.0);
        this.purchasedCost = purchaseItem.getPurchaseCost();
        this.costPerItem = (Math.round((purchaseItem.getPurchaseCost() / purchaseItem.getQuantity()) * 100.0) / 100.0);
        this.purchasedSalesTax = purchaseItem.getPurchaseSalesTax();
        this.quantity = purchaseItem.getQuantity();
    }
}
