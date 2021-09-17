package com.denis.store.utility.populator;

import com.denis.domain.Category;
import com.denis.domain.Product;

import java.util.List;

public interface Populator {

    List<Category> getAllCategories();

    void addToCart(Product product);
}
