package com.retail.service;

import com.retail.wrapper.BillingResponse;
import com.retail.wrapper.PurchaseItemRequest;

import java.util.List;

/**
 * Represents method declaration of Billing related CRUD operations
 * Created by Mahesh Maykarkar on 26/01/19.
 */

public interface BillingManagementService {

    public BillingResponse createBill(List<PurchaseItemRequest> purchaseItemRequestList);

    public BillingResponse getBillingDetails(int billingId);
}
