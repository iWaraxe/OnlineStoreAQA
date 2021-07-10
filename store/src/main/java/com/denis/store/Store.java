package com.denis.store;

import com.denis.domain.Category;
import com.denis.domain.Product;
import com.denis.store.utility.CommandSortComparator;
import com.denis.store.utility.CommandTopComparator;
import com.denis.store.utility.RandomStorePopulator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
        StringBuilder printStore = new StringBuilder();
        for (Category category : categoryList) {
            printStore.append(category.toString());
            printStore.append("\n");
        }

        return "WILDEN's ONLINE STORE: \n" + printStore;
    }

    public void print() {
        System.out.println(this);
    }

    public void printSort() {
        CommandSortComparator commandSortComparator = new CommandSortComparator();
        List<Category> cloneCategory = new ArrayList<>();
        for (Category category : categoryList) {
            ArrayList<Product> copyList = new ArrayList<>();
            for (Product product : category.getProductList()) {
                copyList.add(
                        new Product(product.getName(), product.getRating(), product.getPrice())
                );
            }

            Category cloneCat = new Category(category.getName());
            cloneCat.setProductList(copyList);//copy product
            cloneCategory.add(cloneCat);
            Collections.sort(cloneCat.getProductList(), commandSortComparator);
        }
        StringBuilder printStore = new StringBuilder();
        printStore.append("Store sorted using a config XML file!!!\n");
        for (Category category : cloneCategory) {
            printStore.append(category.toString());
            printStore.append("\n");
        }
        System.out.println(printStore);
    }

    public void printTopPrice() {
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

        Comparator priceComparator = new CommandTopComparator();
        for (Category cat : categoryList) {
            allProducts.addAll(cat.getProductList());
        }

        Collections.sort(allProducts, priceComparator);
        int indexCut = (allProducts.size() > 5) ? 5 : allProducts.size();
        return allProducts.subList(0, indexCut);
    }
}
