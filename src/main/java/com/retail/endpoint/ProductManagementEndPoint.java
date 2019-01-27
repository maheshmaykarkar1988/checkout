package com.retail.endpoint;

import com.retail.service.ProductManagementService;
import com.retail.wrapper.CategoryRequest;
import com.retail.wrapper.ProductRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Endpoints related to Product CRUD operations
 * Created by Mahesh Maykarkar on 26/01/19.
 */

@RestController
@RequestMapping(value = {"/products"})
public class ProductManagementEndPoint {

    private static final Logger logger = LoggerFactory.getLogger(ProductManagementEndPoint.class);

    @Autowired
    ProductManagementService productManagementService;

    /**
     * Creates Product
     * @Request {@link ProductRequest}
     * @return HTTP 201
     */
    @PostMapping(value = "/v1")
    public ResponseEntity createCategory(@Valid @RequestBody ProductRequest productRequest) {
        productManagementService.createProduct(productRequest);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
}
