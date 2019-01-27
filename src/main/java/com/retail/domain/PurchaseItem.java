package com.retail.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entity class to represent Purchase items details
 * Created by Mahesh Maykarkar on 26/01/19.
 */

@Entity
@Table(name = "purchase_item")
@Data
public class PurchaseItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity")
    private float quantity;

    @Column(name = "purchase_sales_tax")
    private double purchaseSalesTax;

    @Column(name = "purchase_cost")
    private double purchaseCost;

    @ManyToOne
    private Bill bill;

    public PurchaseItem() {
    }

    public PurchaseItem(Product product, float quantity) {
        this.product = product;
        this.quantity = quantity;
        this.purchaseCost = Math.round(product.getCost() * quantity * 100.0)/100.0;
        if (product.getCategory().getName().equalsIgnoreCase("C")) {
            this.purchaseSalesTax = 0.0;
        } else {
            this.purchaseSalesTax = product.getCategory().getSalesTax() / 100 * this.purchaseCost;
            this.purchaseSalesTax = Math.round(this.purchaseSalesTax*100.0)/100.0;
        }
    }
}
