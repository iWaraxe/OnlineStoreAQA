package com.denis.store.utility.dao;

import com.denis.domain.Product;
import com.denis.store.utility.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductDao {

    public static final String SELECT_ALL_BY_CATEGORY_ID_SQL = "SELECT * FROM Products WHERE category_id = ?";
    public static final String INSERT_SQL = "INSERT INTO Products (category_id, name, rating, price) VALUES (?, ?, ?, ?)";

    public List<Product> getAllByCategoryId(int categoryId) {
        List<Product> result = new ArrayList<>();
        Connection connection = null;

        try {
            connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BY_CATEGORY_ID_SQL);
            preparedStatement.setInt(1, categoryId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int dbCategoryId = resultSet.getInt("category_id");
                String name = resultSet.getString("name");
                double rating = resultSet.getDouble("rating");
                double price = resultSet.getDouble("price");
                result.add(new Product(id, dbCategoryId, name, rating, price));
            }
        } catch (SQLException e) {
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

        return result;
    }

    public void save(Product product) {
        Connection connection = null;

        try {
            connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL);
            preparedStatement.setInt(1, product.getCategoryId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setDouble(3, product.getRating());
            preparedStatement.setDouble(4, product.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
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
}
