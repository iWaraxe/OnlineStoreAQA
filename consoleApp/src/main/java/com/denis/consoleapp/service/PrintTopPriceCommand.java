package com.denis.consoleapp.service;

import com.denis.domain.Category;
import com.denis.domain.Product;
import com.denis.store.Store;
import com.denis.store.utility.CommandSortComparator;

import java.util.*;
import java.util.stream.Collectors;

public class PrintTopPriceCommand implements Command {
    private Store store;

    public PrintTopPriceCommand(Store store) {
        this.store = store;
    }

    @Override
    public void execute() {
        StringBuilder stringBuilder = new StringBuilder();
        List<Product> topSortProducts = getTopSortProduct();
        for (int i = 0; i < topSortProducts.size(); i++) {
            stringBuilder.append(i + 1);
            stringBuilder.append(".");
            stringBuilder.append(topSortProducts.get(i));
            stringBuilder.append("\n");
        }
        System.out.println("Top5 products from all categories sorted via price DESC: ");
        System.out.println(stringBuilder);
    }

    private List<Product> getTopSortProduct() {
        ArrayList<Product> allProducts = new ArrayList<>();
        HashMap sortParams = new HashMap<>();
        sortParams.put("price", "desc");
        Comparator priceComparator = new CommandSortComparator(sortParams);
        for (Category cat : store.getCategoryList()) {
            allProducts.addAll(cat.getProductList());
        }

        Collections.sort(allProducts, priceComparator);
        return allProducts.stream().limit(5).collect(Collectors.toList());
    }
}