package com.denis.store.utility;

import com.denis.domain.Product;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class CommandSortComparator implements Comparator<Product> {
    List<XmlReader.SortRules> sortRules;

    public CommandSortComparator() {
        sortRules = XmlReader.parceXml();
    }

    @Override
    public int compare(Product o1, Product o2) {
        Integer order = 0;
        Integer rule = 1;
        for (XmlReader.SortRules sortRule : sortRules) {
            if (order != 0) {
                break;
            }
            rule = (sortRule.sortRule.equals("asc") ? 1 : -1);
            if (sortRule.keyName.equals("name")) {
                Function<? super Product, ? extends String> f = Product::getName;
                order = f.apply(o1).compareTo(f.apply(o2));
            } else if (sortRule.keyName.equals("price")) {
                Function<? super Product, ? extends Double> f = Product::getPrice;
                order = Double.compare(f.apply(o1), f.apply(o2));
            } else if (sortRule.keyName.equals("rating")) {
                Function<? super Product, ? extends Double> f = Product::getRating;
                order = Double.compare(f.apply(o1), f.apply(o2));
            }
            order *= rule;
        }
        return order;
    }
}
