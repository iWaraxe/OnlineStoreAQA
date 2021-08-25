package com.denis.consoleapp.service;

import com.denis.domain.Category;
import com.denis.domain.Product;
import com.denis.store.Store;
import com.denis.store.utility.CommandSortComparator;
import com.denis.store.utility.XmlReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrintSortHandler extends Handler {

    @Override
    public boolean isAccessibleHandler(String handler) {
        return "sort".equalsIgnoreCase(handler);
    }

    @Override
    public void execute(Store store) {
        try {
            XmlReader xmlReader = new XmlReader();
            CommandSortComparator commandSortComparator = new CommandSortComparator(
                    xmlReader.parceXml("SortParams.xml")
            );
            List<Category> cloneCategory = new ArrayList<>();
            for (Category category : store.getCategoryList()) {
                Category cloneCat = (Category) category.clone();
                List<Product> copyProductsList = new ArrayList<>();
                for (Product product : category.getProductList()) {
                    copyProductsList.add(
                            (Product) product.clone()
                    );
                }
                cloneCat.setProductList(copyProductsList);
                cloneCategory.add(cloneCat);
                Collections.sort(cloneCat.getProductList(), commandSortComparator);
            }
            System.out.println("Store sorted using a config XML file!!!\n");
            System.out.print(printCategories(cloneCategory));
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}