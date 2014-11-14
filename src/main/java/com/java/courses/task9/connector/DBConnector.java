package com.java.courses.task9.connector;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBConnector {

    public Connection getConnection() throws SQLException {
        // Register appropriate driver
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Database driver is missing...");
        }

        // Get resources
        ResourceBundle rb = ResourceBundle.getBundle("db");

        // Build connection string
        String connectionString = new StringBuilder("")
                .append("jdbc:postgresql://")
                .append(rb.getString("db.host"))
                .append(":")
                .append(rb.getString("db.port"))
                .append("/")
                .append(rb.getString("db.name"))
                .toString();
        return DriverManager.getConnection(connectionString,
                rb.getString("db.user"), rb.getString("db.password"));
    }
}
