package com.denis.store.utility.dao;

import com.denis.domain.Category;
import com.denis.domain.Product;
import com.denis.store.utility.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CategoryDao {

    public static final String SELECT_ALL_SQL = "SELECT * FROM Category";
    public static final String SELECT_BY_NAME_SQL = "SELECT * FROM Category WHERE name = ?";
    public static final String INSERT_SQL = "INSERT INTO Category (name) VALUES (?)";
    private final ProductDao productDao = new ProductDao();

    public boolean existsByName(String name) {
        Connection connection = null;

        try {
            connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_NAME_SQL);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
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

    public int saveByName(String name) {
        Connection connection = null;

        try {
            connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, name);
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt("id");
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
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
    }

    public List<Category> getAll() {
        List<Category> result = new ArrayList<>();
        Connection connection = null;

        try {
            connection = ConnectionPool.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_SQL);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Category category = new Category(id, name);
                List<Product> products = productDao.getAllByCategoryId(id);
                category.setProductList(products);
                result.add(category);
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
}