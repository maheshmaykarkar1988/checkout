package com.retail.endpoint;

import com.retail.service.CategoryManagementService;
import com.retail.wrapper.CategoryRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Endpoints related to product Category CRUD operations
 * Created by Mahesh Maykarkar on 26/01/19.
 */

@RestController
@RequestMapping(value = {"/categories"})
public class CategoryManagementEndPoint {

    private static final Logger logger = LoggerFactory.getLogger(CategoryManagementEndPoint.class);

    @Autowired
    private CategoryManagementService categoryManagementService;

    /**
     * Creates Product category
     * @Request {@link CategoryRequest}
     * @return HTTP 201
     */
    @PostMapping(value = "/v1")
    public ResponseEntity createCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        categoryManagementService.createCategory(categoryRequest);
        return new ResponseEntity<Void>(HttpStatus.CREATED);

    }
}
