package com.denis.consoleapp.service;

import com.denis.domain.Category;
import com.denis.domain.Product;
import com.denis.store.Store;
import com.denis.store.utility.CommandSortComparator;

import java.util.*;
import java.util.stream.Collectors;

public class PrintTopPriceHandler extends Handler {
    public PrintTopPriceHandler() {
    }

    @Override
    public boolean isAccessibleHandler(String handler) {
        return "top".equalsIgnoreCase(handler);
    }

    @Override
    public void execute(Store store) {
        Map sortParams = new HashMap<>();
        sortParams.put("price", "desc");

        List<Product> topSortProducts = getTopSortProduct(store, sortParams, 5);
        System.out.println("Top5 products from all categories sorted via price DESC: ");
        for (int i = 0; i < topSortProducts.size(); i++) {
            System.out.println(printProductLine(topSortProducts.get(i))
            );
        }
    }

    private List<Product> getTopSortProduct(Store store, Map sortParams, int count) {
        List<Product> allProducts = new ArrayList<>();

        Comparator priceComparator = new CommandSortComparator(sortParams);
        for (Category cat : store.getCategoryList()) {
            allProducts.addAll(cat.getProductList());
        }

        Collections.sort(allProducts, priceComparator);
        return allProducts.stream().limit(count).collect(Collectors.toList());
    }
}