package com.retail.wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Responsible for holding product request
 * Created by Mahesh Maykarkar on 26/01/19.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ProductRequest {

    @NotNull(message = "BarCode of the product must not be null")
    @Size(min = 1, message = "BarCode must not be blank")
    private String barCodeId;

    @NotNull(message = "Name of the Product must not be null")
    @Size(min = 1, message = "Name of the Product must not be blank")
    private String name;

    @NotNull(message = "Category of the Product must not be null")
    @Size(min = 1, message = "Name of the Category must not be blank")
    private String category;

    private double cost;
}
