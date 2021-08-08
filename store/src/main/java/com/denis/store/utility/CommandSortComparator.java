package com.denis.store.utility;

import com.denis.domain.Product;

import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;

public class CommandSortComparator implements Comparator<Product> {

    Map<String, String> mapRules;

    public CommandSortComparator(Map<String, String> mapRules) {
        this.mapRules = mapRules;
    }

    @Override
    public int compare(Product o1, Product o2) {
        Integer order = 0;
        Integer rule;
        for (String key : this.mapRules.keySet()) {
            rule = (this.mapRules.get(key).equals("asc") ? 1 : -1);
            if (key.equals("name")) {
                Function<? super Product, ? extends String> f = Product::getName;
                order = f.apply(o1).compareTo(f.apply(o2));
            } else if (key.equals("price")) {
                Function<? super Product, ? extends Double> f = Product::getPrice;
                order = Double.compare(f.apply(o1), f.apply(o2));
            } else if (key.equals("rating")) {
                Function<? super Product, ? extends Double> f = Product::getRating;
                order = Double.compare(f.apply(o1), f.apply(o2));
            }
            order *= rule;
        }
        return order;
    }
}
