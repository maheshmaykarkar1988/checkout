package com.retail.wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
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
}
