package com.retail.endpoint;

import com.retail.service.BillingManagementService;
import com.retail.wrapper.BillingResponse;
import com.retail.wrapper.PurchaseItemRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Endpoints related to billing CRUD operations
 * Created by Mahesh Maykarkar on 26/01/19.
 */

@RestController
@RequestMapping(value = {"/bills"})
public class BillingManagementEndPoint {

    private static final Logger logger = LoggerFactory.getLogger(BillingManagementEndPoint.class);

    @Autowired
    BillingManagementService billingManagementService;

    /**
     * Creates Bill for the items purchased
     * @Request List{{@link PurchaseItemRequest}
     * @param purchaseItemRequestList
     * @return HTTP 201
     */
    @PostMapping(value = "/v1")
    public ResponseEntity createCategory(@Valid @RequestBody List<PurchaseItemRequest> purchaseItemRequestList) {
        BillingResponse billingResponse = billingManagementService.createBill(purchaseItemRequestList);
        return new ResponseEntity<BillingResponse>(billingResponse, HttpStatus.CREATED);

    }

    /**
     * Returns bill for given billing Id
     * @param  id
     * @return {@link BillingResponse}
     */
    @GetMapping("v1/{id}")
    public @ResponseBody
    ResponseEntity getBillingDetails(@Valid @PathVariable int id) {
        BillingResponse billingResponseDetails = billingManagementService.getBillingDetails(id);
        return new ResponseEntity<BillingResponse>(billingResponseDetails, HttpStatus.OK);

    }
}
