package com.thefivebros.ecommerce.model;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.thefivebros.shared.MySQL_Connect.getConnection;


public class ProductDAO{

    public static void main(String[] args) {
        getProducts().forEach(System.out::println);
    }

    // This method get products for the Shop page.
    public static List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        try(Connection connection = getConnection()) {
            CallableStatement statement = connection.prepareCall("{CALL sp_get_all_products()}");
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                String id = rs.getString("prod_id");
                String name = rs.getString("prod_name");
                double price = rs.getDouble("prod_price");
                String description = rs.getString("prod_desc");
                products.add(new Product(id, name, price, description));
            }
        } catch(SQLException e) {
            throw new RuntimeException("Database error - " + e.getMessage());
        }
        return products;
    }

    // This method get products for the Admin page.
    public static List<Product> getProductsAdmin() {
        List<Product> products = new ArrayList<>();
        try(Connection connection = getConnection()) {
            CallableStatement statement = connection.prepareCall("{CALL sp_get_all_products_admin()}");
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                String id = rs.getString("prod_id");
                String name = rs.getString("prod_name");
                double price = rs.getDouble("prod_price");
                String description = rs.getString("prod_desc");
                String vendorId = rs.getString("vend_id");
                String vendorName = rs.getString("vend_name");
                products.add(new Product(id, name, price, description, vendorId, vendorName));
            }
        } catch(SQLException e) {
            throw new RuntimeException("Database error - " + e.getMessage());
        }
        return products;
    }
}
