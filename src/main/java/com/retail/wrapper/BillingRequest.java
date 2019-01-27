package com.retail.wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * Responsible for holding billing request
 * Created by Mahesh Maykarkar on 26/01/19.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class BillingRequest {

    private List<PurchaseItemRequest> purchaseItems;
}
