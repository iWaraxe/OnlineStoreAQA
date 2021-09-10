package com.denis.store;

import com.denis.domain.Category;
import com.denis.domain.Product;
import com.denis.store.utility.populator.Populator;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<Product> purchasedItems = new ArrayList<>();
    private final Populator populator;

    public Store(Populator populator) {
        this.populator = populator;
    }

    public List<Category> getCategoryList() {
        return populator.getRandomCategory();
    }

    public List<Product> getPurchasedItems() {
        return purchasedItems;
    }
}