package com.retail.wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.retail.domain.Bill;
import com.retail.domain.PurchaseItem;
import lombok.Data;

import java.util.List;

/**
 * Responsible for holding billing response
 * Created by Mahesh Maykarkar on 26/01/19.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class BillingResponse {

    private Long billingId;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private double totalCost;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private double totalSalesTax;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private double totalBillAmount;
    private List<PurchaseItemResponse> purchaseItems;

    public BillingResponse() {
    }

    public BillingResponse(Bill bill) {
        this.billingId = bill.getId();
    }

    /**
     * Maps final billing response
     * @param billingResponse
     * @param purchaseItem
     */
    public void setResponseDetails(BillingResponse billingResponse, PurchaseItem purchaseItem){
        billingResponse.setBillingId(purchaseItem.getBill().getId());
        billingResponse.setTotalCost(purchaseItem.getBill().getTotalCost());
        billingResponse.setTotalSalesTax(purchaseItem.getBill().getTotalSalesTax());
        billingResponse.setTotalBillAmount(Math.round((purchaseItem.getBill().getTotalCost() + purchaseItem.getBill().getTotalSalesTax()) * 100.0)/100.0);
    }
}
