package com.denis.store.utility;

import com.denis.domain.Category;
import com.denis.domain.Product;
import com.denis.store.utility.dao.CategoryDao;
import com.denis.store.utility.dao.ProductDao;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class RandomStorePopulator {
    public static List<Category> fakerCategory = new ArrayList<>();
    private static final CategoryDao categoryDao = new CategoryDao();
    private static final ProductDao productDao = new ProductDao();

    public RandomStorePopulator() throws IllegalAccessException, InstantiationException {
        List<Category> categories = categoryDao.getAll();
        Faker faker = new Faker();

        for (Category category : categories) {
            for (int i = 0; i < 3; i++) {
                switch (category.getName()) {
                    case "Book":
                        Product bookProduct = getRandomProduct(faker.book().title());
                        productDao.save(bookProduct, category);
                        break;
                    case "Beer":
                        Product beerProduct = getRandomProduct(faker.beer().name());
                        productDao.save(beerProduct, category);
                        break;
                    case "Food":
                        Product foodProduct = getRandomProduct(faker.food().sushi());
                        productDao.save(foodProduct, category);
                        break;
                    default:
                        Product otherProduct = getRandomProduct(faker.space().company());
                        productDao.save(otherProduct, category);
                        break;
                }
            }

            fakerCategory = categoryDao.getAll();
        }
    }

    private Product getRandomProduct(String productName) {
        return new Product(productName,
                new Faker().number().randomDouble(1, 1, 10),
                new Faker().number().randomDouble(1, 1, 100));
    }

    public List<Category> getRandomCategory() {
        return fakerCategory;
    }
}