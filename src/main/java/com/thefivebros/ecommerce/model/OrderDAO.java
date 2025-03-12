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

    // Get products for customer view
    public static List<Order> getOrders() {
        List<Order> orders = new ArrayList<>();
        try(Connection connection = getConnection()) {
            CallableStatement statement = connection.prepareCall("{CALL sp_get_all_orders_admin()}");
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                int order_num = rs.getInt("order_num");
                Instant order_date = rs.getTimestamp("order_date").toInstant();
                String cust_id = rs.getString("cust_id");
                String cust_name = rs.getString("cust_name");
                orders.add(new Order(order_num, order_date, cust_id, cust_name));
            }
        } catch(SQLException e) {
            throw new RuntimeException("Query error - " +  e.getMessage());
        }
        return orders;
    }
}
