package com.denis.store.utility;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class DBInitializer {

    public static void init() {
        Connection connection = null;

        try {
            connection = ConnectionPool.getConnection();
            Statement statement = connection.createStatement();

            try {
                statement.executeUpdate("DROP TABLE Products");
                statement.executeUpdate("DROP TABLE Category");
            } catch (Exception ignored) {
            }

            statement.executeUpdate("CREATE TABLE Category " +
                    "(id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(255))");
            statement.executeUpdate("CREATE TABLE Products " +
                    "(id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "category_id INT NOT NULL, " +
                    "name VARCHAR(255) NOT NULL, " +
                    "rating DOUBLE PRECISION, " +
                    "price DOUBLE PRECISION, " +
                    "foreign key (category_id) references Category(id))");
            connection.commit();
            connection.close();
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
