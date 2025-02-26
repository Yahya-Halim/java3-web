package com.thefivebros.shared;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL_Connect {
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL driver not found.");
        }

        // Try fetching connection string from environment variable
        String connectionString = System.getenv("AZURE_MYSQL_CONNECTIONSTRING");

        if (connectionString == null || connectionString.isEmpty()) {
            throw new SQLException("Connection string not found in environment variables.");
        }

        try {
            Connection connection = DriverManager.getConnection(connectionString);
            if (connection != null && connection.isValid(2)) {
                return connection;
            } else {
                throw new SQLException("Failed to establish a valid connection.");
            }
        } catch (SQLException e) {
            throw new SQLException("Database connection error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            Connection conn = getConnection();
            if (conn != null) {
                System.out.println("Connection successful!");
            }
        } catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
        }
    }
}
