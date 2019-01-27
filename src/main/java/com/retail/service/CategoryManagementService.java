package com.retail.service;

import com.retail.wrapper.CategoryRequest;

/**
 * Represents method declaration of product's Category related CRUD operations
 * Created by Mahesh Maykarkar on 26/01/19.
 */

public interface CategoryManagementService {

    public void createCategory(CategoryRequest categoryRequest);

}
