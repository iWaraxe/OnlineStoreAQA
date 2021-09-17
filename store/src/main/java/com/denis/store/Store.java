package com.denis.store;

import com.denis.domain.Category;
import com.denis.domain.Product;
import com.denis.store.utility.populator.Populator;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private static Store store;
    private final Populator populator;
    private List<Product> purchasedItems = new ArrayList<>();

    private Store(Populator populator) {
        this.populator = populator;
    }

    public static Store getInstance(Populator populator) {
        if (store == null) {
            store = new Store(populator);
        }
        return store;
    }

    public static Store getInstance() {
        if (store == null) {
            throw new RuntimeException("Store is not initialized");
        }
        return store;
    }

    public List<Category> getCategoryList() {
        return populator.getAllCategories();
    }

    public List<Product> getPurchasedItems() {
        return purchasedItems;
    }

    public Populator getPopulator() {
        return populator;
    }
}