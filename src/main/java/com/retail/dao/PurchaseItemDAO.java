package com.retail.dao;

import com.retail.domain.PurchaseItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Contains database related operations for Purchase Item entity
 * Created by Mahesh Maykarkar on 26/01/19.
 */

public interface PurchaseItemDAO extends JpaRepository<PurchaseItem, Long> {

    @Query("select pi from PurchaseItem pi where pi.bill.id = :id")
    public List<PurchaseItem> findByBillingId(@Param("id") Long id);
}
