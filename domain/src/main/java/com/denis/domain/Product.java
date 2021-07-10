package com.denis.domain;

public class Product {
    private String name;
    private double rating;
    private double price;

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

    @Override
    public String toString() {
        String printProduct = String.format("Name: '%s', Rating: %s, Price: %s", name, rating, price);
        return printProduct;
    }
}
