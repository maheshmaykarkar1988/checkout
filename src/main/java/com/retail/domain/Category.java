package com.retail.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * Entity class to represent product Category details
 * Created by Mahesh Maykarkar on 26/01/19.
 */

@Entity
@Table(name = "category")
@Data
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "name")
    @NotBlank
    private String name;

    @Column(name = "sales_tax")
    private double salesTax;

    public Category() {
    }

    public Category(@NotBlank String name, double salesTax) {
        this.name = name;
        this.salesTax = salesTax;
    }

    public Category(String name) {
        this.name = name;
    }
}
