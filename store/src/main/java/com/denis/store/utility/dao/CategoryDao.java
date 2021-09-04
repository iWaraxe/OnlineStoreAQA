package com.denis.store.utility.dao;

import com.denis.domain.Category;
import com.denis.domain.Product;
import com.denis.store.utility.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {

    public static final String SELECT_ALL_SQL = "SELECT * FROM Category";
    public static final String SELECT_BY_NAME_SQL = "SELECT * FROM Category WHERE name = ?";
    public static final String INSERT_SQL = "INSERT INTO Category VALUES (?)";
    private final ProductDao productDao = new ProductDao();

    public boolean existsByName(String name) {

        try (Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_NAME_SQL);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public void saveByName(String name) {

        try (Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INSERT_SQL);
            statement.setString(1, name);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public List<Category> getAll() {
        List<Category> result = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_SQL);

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                Category category = new Category(name);
                List<Product> products = productDao.getAllByCategoryName(name);
                category.setProductList(products);
                result.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return result;
    }
}
