package com.denis.store;

import com.denis.domain.Category;
import com.denis.domain.Product;
import com.denis.store.utility.RandomStorePopulator;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<Category> categoryList;
    private List<Product> purchasedItems = new ArrayList<>();

    public Store() {
        try {
            this.categoryList = new RandomStorePopulator().getRandomCategory();
        } catch (Exception exception) {
            System.out.println("ERROR: RandomStorePopulator = " + exception.getMessage());
        }
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public List<Product> getPurchasedItems() {
        return purchasedItems;
    }
}