package com.retail.wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Responsible for holding product request
 * Created by Mahesh Maykarkar on 26/01/19.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ProductRequest {

    @NotNull(message = "BarCode of the product must not be null")
    private String barCodeId;

    @NotNull(message = "Name of the product must not be null")
    private String name;

    @NotNull(message = "Category of the product must not be null")
    private String category;

    private double cost;
}
