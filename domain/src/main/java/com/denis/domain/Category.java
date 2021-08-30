package com.denis.domain;

import java.util.List;

public class Category implements Cloneable {

    private String name;
    protected List<Product> productList;

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}