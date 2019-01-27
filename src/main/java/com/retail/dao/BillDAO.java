package com.retail.dao;

import com.retail.domain.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Contains database related operations for Billing entity
 * Created by Mahesh Maykarkar on 26/01/19.
 */

public interface BillDAO extends JpaRepository<Bill, Long> {
}
