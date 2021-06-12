package com.denis.store;

import com.denis.domain.Category;
import com.denis.store.utility.RandomStorePopulator;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<Category> categoryList;

    public Store() {
        try {
            this.categoryList = new RandomStorePopulator().getRandomCategory();
        } catch (Exception exception) {
            System.out.println("ERROR: RandomStorePopulator = " + exception.getMessage());
        }
    }

    @Override
    public String toString() {
        List<String> printStore = new ArrayList<>();
        for (Category category : categoryList) {
            printStore.add(category.toString());
        }

        return "WILDEN's ONLINE STORE: \n" + String.join("\n", printStore);
    }

    public void print() {
        System.out.println(this);
    }
}
