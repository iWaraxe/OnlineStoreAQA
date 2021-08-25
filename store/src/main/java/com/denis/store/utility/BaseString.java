package com.denis.store.utility;

import com.denis.domain.Category;
import com.denis.domain.Product;
import com.denis.store.Store;

import java.util.List;

public class BaseString {

    public String printCategories(List<Category> categoryList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Category category : categoryList) {
            stringBuilder.append(printCategoryLine(category));
        }
        return stringBuilder.toString();
    }

    public String printCategoryLine(Category category) {
        StringBuilder printStore = new StringBuilder();
        printStore.append(printCategory(category));
        printStore.append("\n");
        return printStore.toString();
    }

    public String printCategory(Category c) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(c.getName());
        stringBuilder.append(" for sale: \n");

        for (Product product : c.getProductList()) {
            stringBuilder.append(printProductLine(product));
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public String printStore(Store store) {
        StringBuilder printStore = new StringBuilder();
        printStore.append("WILDEN's ONLINE STORE:");
        printStore.append("\n");
        for (Category category : store.getCategoryList()) {
            printStore.append(printCategory(category));
            printStore.append("\n");
        }
        return printStore.toString();
    }

    public String printProductLine(Product product) {
        String printProduct = String.format(
                "Name: '%s', Rating: %s, Price: %s",
                product.getName(),
                product.getRating(),
                product.getPrice()
        );
        return printProduct;
    }
}