package com.retail.serviceImpl;

import com.retail.dao.CategoryDAO;
import com.retail.domain.Category;
import com.retail.exception.RetailValidationException;
import com.retail.service.CategoryManagementService;
import com.retail.utils.CategoryType;
import com.retail.wrapper.CategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * Represents actual implementations of CRUD operations related to product's category
 * Created by Mahesh Maykarkar on 26/01/19.
 */

@Service
public class CategoryManagementServiceImpl implements CategoryManagementService {

    @Autowired
    CategoryDAO categoryDAO;

    @Value("${category.value.A}")
    private int categoryAValue;

    @Value("${category.value.B}")
    private int categoryBValue;

    @Override
    @Transactional
    public void createCategory(CategoryRequest categoryRequest) {

        verifyIfCategory(categoryRequest.getName());

        Category category = new Category(categoryRequest.getName());

        if (categoryRequest.getName().equalsIgnoreCase("A")) {
            category.setSalesTax(categoryAValue);
        } else {
            if (categoryRequest.getName().equalsIgnoreCase("B"))
                category.setSalesTax(categoryBValue);
        }

        categoryDAO.save(category);
    }

    private void verifyIfCategory(String name) {
        if (name.equalsIgnoreCase("A") || name.equalsIgnoreCase("B") || name.equalsIgnoreCase("C")) {
            Category category = categoryDAO.findCategoryByName(name);
            if (!Objects.isNull(category)) {
                throw new RetailValidationException("Category " + name + " already exists");
            }
        } else {
            throw new RetailValidationException("System allows only to have category type as A, B or C");
        }
    }
}
