package com.retail.dao;

import com.retail.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Contains database related operations for product's category entity
 * Created by Mahesh Maykarkar on 26/01/19.
 */

public interface CategoryDAO extends JpaRepository<Category, Long> {
    @Query("SELECT c FROM Category c WHERE c.name = :name")
    Category findCategoryByName(@Param("name") String name);


}
