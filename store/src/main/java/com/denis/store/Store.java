package com.denis.store;

import com.denis.domain.Category;
import com.denis.domain.Product;
import com.denis.store.utility.RandomStorePopulator;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<Category> categoryList;
    private List<Product> purchasedItems = new ArrayList<>();
    private static Store store;

    private Store() {
        try {
            this.categoryList = new RandomStorePopulator().getRandomCategory();
        } catch (Exception exception) {
            System.out.println("ERROR: RandomStorePopulator = " + exception.getMessage());
        }
    }

    public static Store getInstance() {
        if (store == null) {
            store = new Store();
        }
        return store;
    }

    @Override
    public String toString() {
        StringBuilder printStore = new StringBuilder();
        for (Category category : categoryList) {
            printStore.append(category.toString());
            printStore.append("\n");
        }

        return "WILDEN's ONLINE STORE: \n" + printStore;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public List<Product> getPurchasedItems() {
        return purchasedItems;
    }
}