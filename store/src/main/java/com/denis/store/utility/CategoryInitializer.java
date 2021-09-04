package com.denis.store.utility;

import com.denis.domain.categories.Beer;
import com.denis.domain.categories.Book;
import com.denis.domain.categories.Food;
import com.denis.store.utility.dao.CategoryDao;

public class CategoryInitializer {

    private static final CategoryDao categoryDao = new CategoryDao();

    public static void init() {
        save(Beer.class.getSimpleName());
        save(Book.class.getSimpleName());
        save(Food.class.getSimpleName());
    }

    private static void save(String name) {
        categoryDao.saveByName(name);
    }
}
