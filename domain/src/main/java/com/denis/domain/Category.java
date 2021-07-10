package com.denis.domain;

import java.util.ArrayList;
import java.util.List;

public class Category {

    private String name;
    protected List<Product> productList;

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        List<String> printCategory = new ArrayList<>();
        for (Product product : productList) {
            printCategory.add(product.toString());
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Product product : productList) {
            stringBuilder.append(product.toString());
            stringBuilder.append("\n");
        }
        return this.name + " for sale: \n" + stringBuilder;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<Product> getProductList() {
        return productList;
    }
}
