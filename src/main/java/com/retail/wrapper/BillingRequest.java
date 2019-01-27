package com.retail.wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * Responsible for holding billing request
 * Created by Mahesh Maykarkar on 26/01/19.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class BillingRequest {

    @Size(min = 1, message = "Item list must not be blank")
    private List<PurchaseItemRequest> purchaseItems;
}
