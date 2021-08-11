package com.denis.consoleapp.service;

import com.denis.domain.Category;
import com.denis.domain.Product;
import com.denis.store.Store;
import com.denis.store.utility.CommandSortComparator;
import com.denis.store.utility.XmlReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrintSortCommand implements Command {
    private Store store;

    public PrintSortCommand(Store store) {
        this.store = store;
    }

    @Override
    public void execute() {
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
}