package com.denis.consoleapp.service;

import com.denis.domain.Category;
import com.denis.domain.Product;
import com.denis.store.Store;
import com.denis.store.utility.CommandSortComparator;
import com.denis.store.utility.XmlReader;

import java.util.*;
import java.util.stream.Collectors;

public class CommandService {
    private Store store;

    public CommandService(Store store) {
        this.store = store;
    }

    public void printStore() {
        System.out.println(store);
    }

    public void printSort() {
        try {
            XmlReader xmlReader = new XmlReader();
            CommandSortComparator commandSortComparator = new CommandSortComparator(
                    xmlReader.parceXml("SortParams.xml")
            );
            List<Category> cloneCategory = new ArrayList<>();
            for (Category category : store.getCategoryList()) {
                Category cloneCat = (Category) category.clone();
                ArrayList<Product> copyProductsList = new ArrayList<>();
                for (Product product : category.getProductList()) {
                    copyProductsList.add(
                            (Product) product.clone()
                    );
                }
                cloneCat.setProductList(copyProductsList);
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
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
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
