package com.retail.wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * Responsible for holding purchase item request
 * Created by Mahesh Maykarkar on 26/01/19.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class PurchaseItemRequest {

    private String barCodeId;
    private float quantity;
}
