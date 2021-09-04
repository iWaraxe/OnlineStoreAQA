package com.denis.store.utility.dao;

import com.denis.domain.Category;
import com.denis.domain.Product;
import com.denis.store.utility.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    public static final String SELECT_ALL_BY_CATEGORY_NAME_SQL = "SELECT * FROM Products WHERE category_name = ?";
    public static final String INSERT_SQL = "INSERT INTO Products VALUES (?, ?, ?, ?)";

    public List<Product> getAllByCategoryName(String categoryName) {
        List<Product> result = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BY_CATEGORY_NAME_SQL);
            preparedStatement.setString(1, categoryName);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                double rating = resultSet.getDouble("rating");
                double price = resultSet.getDouble("price");
                result.add(new Product(name, rating, price));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        return result;
    }

    public void save(Product product, Category category) {
        try (Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL);
            preparedStatement.setString(1, category.getName());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setDouble(3, product.getRating());
            preparedStatement.setDouble(4, product.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
