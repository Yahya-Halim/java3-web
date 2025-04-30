package com.thefivebros.ecommerce.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.thefivebros.shared.MySQL_Connect.getConnection;

public class ProductDAO{

    public static void main(String[] args) {
        System.out.println(getProduct("DOL0011"));
    }

    // This method get products for the Shop page.
    public static List<Product> getProducts(int limit, int offset, String categories, Double minPrice, Double maxPrice, String sort, String search) {
        List<Product> products = new ArrayList<>();
        try (Connection connection = getConnection()) {
            CallableStatement statement = connection.prepareCall("{CALL sp_get_all_products(?, ?, ?, ?, ?, ?)}");
            statement.setInt(1, limit);
            statement.setInt(2, offset);
            statement.setString(3, categories != null ? categories : "");

            if (minPrice != null) {
                statement.setDouble(4, minPrice);
            } else {
                statement.setNull(4, Types.DECIMAL);
            }

            if (maxPrice != null) {
                statement.setDouble(5, maxPrice);
            } else {
                statement.setNull(5, Types.DECIMAL);
            }

            statement.setString(6, search != null ? search : "");

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String id = rs.getString("prod_id");
                String name = rs.getString("prod_name");
                double price = rs.getDouble("prod_price");
                String description = rs.getString("prod_desc");
                int categoryId = rs.getInt("category_id");
                String categoryName = rs.getString("category_name");
                products.add(new Product(id, name, price, description, categoryId, categoryName));
            }

            // Sort in Java (if required)
            if ("az".equalsIgnoreCase(sort)) {
                products.sort(Comparator.comparing(Product::getName, String.CASE_INSENSITIVE_ORDER));
            } else if ("za".equalsIgnoreCase(sort)) {
                products.sort((p1, p2) -> p2.getName().compareToIgnoreCase(p1.getName()));
            }

        } catch (SQLException e) {
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

    public static int getProductCount(String categories) {
        try(Connection connection = getConnection();
            CallableStatement statement = connection.prepareCall("{CALL sp_get_total_products(?)}");
        ) {
            statement.setString(1, categories);
            try(ResultSet resultSet = statement.executeQuery();) {
                if (resultSet.next()) {
                    return resultSet.getInt("total_products");
                }
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public static Product getProduct(String id) {
        Product product = null;
        try(Connection connection = getConnection()) {
            CallableStatement statement = connection.prepareCall("{CALL sp_get_product(?)}");
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                String prod_id = rs.getString("prod_id");
                String name = rs.getString("prod_name");
                double price = rs.getDouble("prod_price");
                String description = rs.getString("prod_desc");
                product = new Product(prod_id, name, price, description);
            }
        } catch(SQLException e) {
            throw new RuntimeException("Database error - " + e.getMessage());
        }
        return product;
    }
}
