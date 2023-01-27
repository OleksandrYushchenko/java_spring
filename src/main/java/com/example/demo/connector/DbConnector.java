package com.example.demo.connector;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
@Component
public class DbConnector {
    private String url;
    private Connection connection;
    public DbConnector (@Value("${spring.datasource.url}") String url) {
        this.url = url;
        String connectionUrl = url;
        try {
            this.connection = DriverManager.getConnection(connectionUrl);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection() {
        return connection;
    }
}

