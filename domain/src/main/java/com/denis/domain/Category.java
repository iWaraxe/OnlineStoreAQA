package com.denis.domain;

import com.denis.store.utility.dao.CategoryDao;

import java.util.List;

public class Category implements Cloneable {

    private int id;
    private String name;
    protected List<Product> productList;

    public Category(String name) {
        this.name = name;
        CategoryDao categoryDao = new CategoryDao();
        if (!categoryDao.existsByName(name)) {
            categoryDao.saveByName(name);
        }
    }

    public Category(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
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