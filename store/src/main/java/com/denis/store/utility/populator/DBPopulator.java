package com.denis.store.utility.populator;

import com.denis.domain.Category;
import com.denis.domain.Product;
import com.denis.store.Store;
import com.denis.store.utility.ConnectionPool;
import com.denis.store.utility.dao.CategoryDao;
import com.denis.store.utility.dao.ProductDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

public class DBPopulator implements Populator {

    private final CategoryDao categoryDao = new CategoryDao();
    private final ProductDao productDao = new ProductDao();

    static {
        Connection connection = null;

        try {
            connection = ConnectionPool.getConnection();
            Statement statement = connection.createStatement();

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Category " +
                    "(id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(255))");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Products " +
                    "(id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "category_id INT NOT NULL, " +
                    "name VARCHAR(255) NOT NULL, " +
                    "rating DOUBLE PRECISION, " +
                    "price DOUBLE PRECISION, " +
                    "foreign key (category_id) references Category(id))");
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            if (Objects.nonNull(connection)) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }


    @Override
    public List<Category> getAllCategories() {
        List<Category> categories;

        try {
            categories = new RandomStorePopulator().getAllCategories();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }

        for (Category category : categories) {
            if (!categoryDao.existsByName(category.getName())){
                int id = categoryDao.saveByName(category.getName());
                category.setId(id);
                for (Product product : category.getProductList()) {
                    product.setCategoryId(category.getId());
                    productDao.save(product);
                }
            }
        }

        return categoryDao.getAll();
    }

    @Override
    public void addToCart(Product product) {
        Store store = Store.getInstance();
        store.getPurchasedItems().add(product);
    }
}
