package com.denis.store.utility;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {

    private static BasicDataSource ds = new BasicDataSource();

    static {
        ds.setUrl(Config.getProperty("db.url"));
        ds.setUsername(Config.getProperty("db.user"));
        ds.setPassword(Config.getProperty("db.password"));
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    private ConnectionPool() {
    }
}
