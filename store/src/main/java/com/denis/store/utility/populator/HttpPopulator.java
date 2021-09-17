package com.denis.store.utility.populator;

import com.denis.domain.Category;
import com.denis.domain.Product;
import com.denis.store.utility.http.HttpClient;

import java.util.List;

public class HttpPopulator implements Populator {

    private final HttpClient httpClient = new HttpClient();

    @Override
    public List<Category> getAllCategories() {
        return httpClient.getAllCategories();
    }

    @Override
    public void addToCart(Product product) {
        httpClient.addProductToCart(product);
    }
}
