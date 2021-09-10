package com.denis.store.utility.populator;

import com.denis.domain.Category;
import com.denis.domain.Product;
import com.denis.store.utility.dao.CategoryDao;
import com.denis.store.utility.dao.ProductDao;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class RandomStorePopulator implements Populator {
    public static List<Category> fakerCategory = new ArrayList<>();
    private static final CategoryDao categoryDao = new CategoryDao();
    private static final ProductDao productDao = new ProductDao();

    public RandomStorePopulator() {
        List<Category> categories = categoryDao.getAll();
        Faker faker = new Faker();

        for (Category category : categories) {
            for (int i = 0; i < 3; i++) {
                switch (category.getName()) {
                    case "Book":
                        Product bookProduct = getRandomProduct(faker.book().title(), category);
                        productDao.save(bookProduct);
                        break;
                    case "Beer":
                        Product beerProduct = getRandomProduct(faker.beer().name(), category);
                        productDao.save(beerProduct);
                        break;
                    case "Food":
                        Product foodProduct = getRandomProduct(faker.food().sushi(), category);
                        productDao.save(foodProduct);
                        break;
                    default:
                        Product otherProduct = getRandomProduct(faker.space().company(), category);
                        productDao.save(otherProduct);
                        break;
                }
            }

            fakerCategory = categoryDao.getAll();
        }
    }

    private Product getRandomProduct(String productName, Category category) {
        return new Product(category.getId(), productName,
                new Faker().number().randomDouble(1, 1, 10),
                new Faker().number().randomDouble(1, 1, 100));
    }

    public List<Category> getRandomCategory() {
        return fakerCategory;
    }
}