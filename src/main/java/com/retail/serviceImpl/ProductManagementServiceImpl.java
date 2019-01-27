package com.retail.serviceImpl;

import com.retail.dao.CategoryDAO;
import com.retail.dao.ProductDAO;
import com.retail.domain.Category;
import com.retail.domain.Product;
import com.retail.exception.RetailValidationException;
import com.retail.service.ProductManagementService;
import com.retail.wrapper.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Represents actual implementations of CRUD operations related to Product
 * Created by Mahesh Maykarkar on 26/01/19.
 */

@Service
public class ProductManagementServiceImpl implements ProductManagementService {

    @Autowired
    ProductDAO productDAO;

    @Autowired
    CategoryDAO categoryDAO;

    @Override
    public void createProduct(ProductRequest productRequest) {

        verifyIfProductExists(productRequest.getBarCodeId());

        Category category = categoryDAO.findCategoryByName(productRequest.getCategory());

        if (Objects.isNull(category)) {
            throw new RetailValidationException("Invalid Category type");
        }

        Product product = new Product(productRequest.getName(), productRequest.getCost(), category, productRequest.getBarCodeId());

        productDAO.save(product);
    }

    private void verifyIfProductExists(String barCodeId) {
        Product product = productDAO.findByBarCodeId(barCodeId);
        if (!Objects.isNull(product)) {
            throw new RetailValidationException("Product with BarCode ID  " + barCodeId + " already exists");
        }
    }
}
