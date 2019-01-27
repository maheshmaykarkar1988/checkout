package com.retail.exception;

import lombok.Data;

/**
 * ustom exception class to handle validations scenarios
 * Created by Mahesh Maykarkar on 26/01/19.
 */

@Data
public class RetailValidationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String errorMessage;

    public RetailValidationException() {
        super();
    }

    public RetailValidationException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
}
