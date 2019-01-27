package com.retail.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * Entity class to represent Product details
 * Created by Mahesh Maykarkar on 26/01/19.
 */

@Entity
@Table(name = "product")
@Data
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NotBlank
    private String name;

    @Column(name = "cost")
    private double cost;

    @Column(name = "barCode_Id", unique = true)
    @NotBlank
    private String barCodeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    public Product() {
    }

    public Product(String name, double cost, Category category, String barCodeId) {
        this.name = name;
        this.cost = cost;
        this.category = category;
        this.barCodeId = barCodeId;
    }
}
