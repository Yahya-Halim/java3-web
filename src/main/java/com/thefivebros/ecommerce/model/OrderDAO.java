package com.thefivebros.ecommerce.model;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static com.thefivebros.shared.MySQL_Connect.getConnection;


public class OrderDAO {
    public static void main(String[] args) {
        getOrders().forEach(System.out::println);
    }

    // This method get products for the Shop page.
    public static List<Order> getOrders() {
        List<Order> orders = new ArrayList<>();
        try(Connection connection = getConnection()) {
            CallableStatement statement = connection.prepareCall("{CALL sp_get_all_orders_admin()}");
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("order_num");
                Instant orderDate = rs.getTimestamp("order_date").toInstant();
                String customerID = rs.getString("cust_id");
                String customerName = rs.getString("cust_name");
                orders.add(new Order(id, orderDate, customerID, customerName));
            }
        } catch(SQLException e) {
            throw new RuntimeException("Database error - " + e.getMessage());
        }
        return orders;
    }
}
