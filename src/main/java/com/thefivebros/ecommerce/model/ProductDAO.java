package com.thefivebros.ecommerce.model;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.thefivebros.shared.MySQL_Connect.getConnection;

public class ProductDAO {


    // get product for customer view
    public static List<Product> getProducts(){
        List<Product> products = new ArrayList<>();
        try (Connection connection = getConnection()){
            CallableStatement statement = connection.prepareCall("{CALL sp_get_all_products()}");
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                String prod_id = rs.getString("prod_id");
                String prod_name = rs.getString("prod_name");
                double prod_price = rs.getDouble("prod_price");
                String prod_desc = rs.getString("prod_desc");

                products.add(new Product(prod_id, prod_name, prod_price, prod_desc));


            }
        } catch (SQLException e){
            throw new RuntimeException("Query error: " + e.getMessage());

        }
        return products;
    }
    // get product for customer view
    public static List<Product> getProductsAdmin(){
        List<Product> products = new ArrayList<>();
        try (Connection connection = getConnection()){
            CallableStatement statement = connection.prepareCall("{CALL sp_get_all_products_admin()}");
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                String prod_id = rs.getString("prod_id");
                String prod_name = rs.getString("prod_name");
                double prod_price = rs.getDouble("prod_price");
                String prod_desc = rs.getString("prod_desc");
                String vend_id = rs.getString("vend_id");
                String vend_name = rs.getString("vend_name");

                products.add(new Product(prod_id, prod_name, prod_price, prod_desc, vend_id,vend_name ));


            }
        } catch (SQLException e){
            throw new RuntimeException("Query error: " + e.getMessage());

        }
        return products;
    }

    public static void main(String[] args) {
        getProductsAdmin().forEach(System.out::println);
    }
}
