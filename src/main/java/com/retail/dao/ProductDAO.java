package com.retail.dao;

import com.retail.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Contains database related operations for Product entity
 * Created by Mahesh Maykarkar on 26/01/19.
 */

public interface ProductDAO extends JpaRepository<Product, Integer> {
    @Query("select p from Product p where p.barCodeId = :barCodeId")
    public Product findByBarCodeId(@Param("barCodeId") String barCodeId);

}
