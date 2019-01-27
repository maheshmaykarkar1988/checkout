package com.retail.utils;

public enum CategoryType {

    A(10),
    B(20),
    C(0);

    private final int categoryTypeValue;

    public int getCategoryTypeValue() {
        return categoryTypeValue;
    }

    CategoryType(int categoryTypeValue) {
        this.categoryTypeValue = categoryTypeValue;
    }
}
