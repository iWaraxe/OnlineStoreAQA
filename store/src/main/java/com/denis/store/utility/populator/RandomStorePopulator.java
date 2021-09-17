package com.denis.store.utility.populator;

import com.denis.domain.Category;
import com.denis.domain.Product;
import com.denis.store.utility.dao.CategoryDao;
import com.denis.store.utility.dao.ProductDao;
import com.github.javafaker.Faker;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RandomStorePopulator implements Populator {
    public static List<Category> fakerCategory = new ArrayList<>();

    public RandomStorePopulator() throws IllegalAccessException, InstantiationException {
        Reflections reflections = new Reflections("com.denis.domain.categories");
        Set<Class<? extends Category>> subClassesForCategory = reflections.getSubTypesOf(Category.class);

        Faker faker = new Faker();
        for (Class<? extends Category> categoryChildClass : subClassesForCategory) {
            Category category = categoryChildClass.newInstance();
            ArrayList<Product> products = new ArrayList<>();

            for (int i = 0; i < 3; i++) {
                switch (category.getName()) {
                    case "Book":
                        products.add(
                                getRandomProduct(faker.book().title()));
                        break;
                    case "Beer":
                        products.add(
                                getRandomProduct(faker.beer().name()));
                        break;
                    case "Food":
                        products.add(
                                getRandomProduct(faker.food().sushi()));
                        break;
                    default:
                        products.add(
                                getRandomProduct(faker.space().company()));
                        break;
                }
            }
            category.setProductList(products);
            fakerCategory.add(category);
        }
    }

    private Product getRandomProduct(String productName) {
        return new Product(productName,
                new Faker().number().randomDouble(1, 1, 10),
                new Faker().number().randomDouble(1, 1, 100));
    }

    public List<Category> getAllCategories() {
        return fakerCategory;
    }
}