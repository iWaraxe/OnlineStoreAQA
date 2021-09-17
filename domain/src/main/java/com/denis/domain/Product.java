package com.denis.domain;

public class Product implements Cloneable {

    private int id;
    private int categoryId;
    private String name;
    private double rating;
    private double price;

    public Product(int id, int categoryId, String name, double rating, double price) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.rating = rating;
        this.price = price;
    }

    public Product(int categoryId, String name, double rating, double price) {
        this.categoryId = categoryId;
        this.name = name;
        this.rating = rating;
        this.price = price;
    }

    public Product(String name, double rating, double price) {
        this.name = name;
        this.rating = rating;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public double getRating() {
        return rating;
    }

    public int getId() {
        return id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}