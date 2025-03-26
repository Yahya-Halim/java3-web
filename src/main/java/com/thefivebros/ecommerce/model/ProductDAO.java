package com.thefivebros.ecommerce.model;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.thefivebros.shared.MySQL_Connect.getConnection;


public class ProductDAO{



    // This method get products for the Shop page.
    public static List<Product> getProducts(int limit, int offset, String categories) {
        List<Product> products = new ArrayList<>();
        try(Connection connection = getConnection()) {
            CallableStatement statement = connection.prepareCall("{CALL sp_get_all_products(?,?,?)}");
            statement.setInt(1, limit);
            statement.setInt(2, offset);
            statement.setString(3, categories);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                String id = rs.getString("prod_id");
                String name = rs.getString("prod_name");
                double price = rs.getDouble("prod_price");
                String description = rs.getString("prod_desc");
                int categoryId = rs.getInt("category_id");
                String categoryName = rs.getString("name");
                products.add(new Product(id, name, price, description, categoryId, categoryName));
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
    public static List<ProductCategory> getAllCategories() {
        List<ProductCategory> categories = new ArrayList<>();
        try (Connection connection = getConnection();
             CallableStatement statement = connection.prepareCall("{CALL sp_get_product_categories()}");
             ResultSet resultSet = statement.executeQuery();
        ) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int numProducts = resultSet.getInt("num_products");
                categories.add(new ProductCategory(id, name, numProducts));
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return categories;
    }
}
