package com.retail.service;

import com.retail.wrapper.ProductRequest;

/**
 * Represents method declaration of Product related CRUD operations
 * Created by Mahesh Maykarkar on 26/01/19.
 */

public interface ProductManagementService {

    public void createProduct(ProductRequest productRequest);
}
