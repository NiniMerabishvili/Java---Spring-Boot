package com.postgresql.NetWorkers.entities;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class DatabaseConfig {

    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/Networkers";
    private static final String USERNAME = "yourusername";
    private static final String PASSWORD = "yourpassword";

    private static final HikariConfig config = new HikariConfig();

    static {
        config.setJdbcUrl(JDBC_URL);
        config.setUsername(USERNAME);
        config.setPassword(PASSWORD);
        config.setDriverClassName("org.postgresql.Driver");

        // Additional HikariCP configuration settings can be added here
    }

    private static final DataSource dataSource = new HikariDataSource(config);

    public static DataSource getDataSource() {
        return dataSource;
    }
}
