package com.retail.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Iterator;
import java.util.Set;

/**
 * Entity class to represent Billing details
 * Created by Mahesh Maykarkar on 26/01/19.
 */

@Entity
@Table(name = "bill")
@Data
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "total_cost")
    private double totalCost;

    @Column(name = "total_sales_tax")
    private double totalSalesTax;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "bill_id")
    private Set<PurchaseItem> purchaseItems;

    public Bill() {
    }

    public void setPurchaseItems(Set<PurchaseItem> purchases) {
        this.purchaseItems = purchases;
        Iterator<PurchaseItem> iterator = this.purchaseItems.iterator();
        while (iterator.hasNext()) {
            PurchaseItem next = iterator.next();
            totalSalesTax = totalSalesTax + next.getPurchaseSalesTax();
            totalCost = totalCost + next.getPurchaseCost();
        }
        this.purchaseItems = purchases;
    }

    /*public Bill(Set<PurchaseItem> purchases) {

    }*/
}
