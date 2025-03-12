package com.thefivebros.ecommerce.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.thefivebros.shared.MySQL_Connect.getConnection;

public class VendorDAO {
    public static List<Vendor> getVendorsAdmin(){
        List<Vendor> vendoresultSet = new ArrayList<>();
        try (Connection connection = getConnection()){
            CallableStatement statement = connection.prepareCall("{CALL sp_get_all_vendors_admin()}");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                String vend_id = resultSet.getString("vend_id");
                String vend_name = resultSet.getString("vend_name");
                String address = resultSet.getString("vend_address");
                String city = resultSet.getString("vend_city");
                String state = resultSet.getString("vend_state");
                String zip = resultSet.getString("vend_zip");
                String country = resultSet.getString("vend_country");

                vendoresultSet.add(new Vendor(vend_id, vend_name, address, city, state, zip, country));
            }


        }catch (SQLException e) {
            throw new RuntimeException("Query error: " + e.getMessage(), e);

        }
        return vendoresultSet;
    }
    public static boolean addVendor(Vendor vendor) {
        try (Connection connection = getConnection()) {
            CallableStatement statement = connection.prepareCall("{CALL sp_add_vendor_admin(?, ?, ?, ?, ?, ?, ?)}");
            statement.setString(1, vendor.getVend_id());
            statement.setString(2, vendor.getVend_name());
            statement.setString(3, vendor.getAddress().getAddress());
            statement.setString(4, vendor.getAddress().getCity());
            statement.setString(5, vendor.getAddress().getState());
            statement.setString(6, vendor.getAddress().getZip());
            statement.setString(7, vendor.getAddress().getCountry());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        } catch(SQLException e) {
            System.out.println(e.getMessage()); // Uncomment
            return false;
        }

    }
    public static boolean updateVendor(Vendor originalVendor, Vendor newVendor) {
        try (Connection connection = getConnection()) {
            CallableStatement statement = connection.prepareCall("{CALL sp_update_vendor_admin(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            statement.setString(1, originalVendor.getVend_id());
            statement.setString(2, originalVendor.getVend_name());
            statement.setString(3, originalVendor.getAddress().getAddress());
            statement.setString(4, originalVendor.getAddress().getCity());
            statement.setString(5, originalVendor.getAddress().getState());
            statement.setString(6, originalVendor.getAddress().getZip());
            statement.setString(7, originalVendor.getAddress().getCountry());
            statement.setString(8, newVendor.getVend_id());
            statement.setString(9, newVendor.getVend_name());
            statement.setString(10, newVendor.getAddress().getAddress());
            statement.setString(11, newVendor.getAddress().getCity());
            statement.setString(12, newVendor.getAddress().getState());
            statement.setString(13, newVendor.getAddress().getZip());
            statement.setString(14, newVendor.getAddress().getCountry());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        } catch(SQLException e) {
            System.out.println(e.getMessage()); // Uncomment
            return false;
        }

    }
    public static void main(String[] args) {
        Vendor vendor = new Vendor("7", "Test Vendor", new Address("123 Example St.", "Cedar Rapids", "IA", "55555", "USA"));
        System.out.println(addVendor(vendor));
    }


    public static Vendor getVendor(String vend_id) {
        Vendor vendor = null;
        if(vend_id != null) {
            vend_id = vend_id.trim();
            try (Connection connection = getConnection()) {
                CallableStatement statement = connection.prepareCall("{CALL sp_get_vendor_by_id(?)}");
                statement.setString(1, vend_id);
                ResultSet resultSet = statement.executeQuery();
                // Use an if statement instead of a while loop when the SELECT query returns one record
                if (resultSet.next()) {
                    String vend_name = resultSet.getString("vend_name");
                    String vend_address = resultSet.getString("vend_address");
                    String vend_city = resultSet.getString("vend_city");
                    String vend_state = resultSet.getString("vend_state");
                    String vend_zip = resultSet.getString("vend_zip");
                    String vend_country = resultSet.getString("vend_country");
                    vendor = new Vendor(vend_id, vend_name, new Address(vend_address, vend_city, vend_state, vend_zip, vend_country));
                }
            } catch (SQLException e) {
//            System.out.println(e.getMessage()); // Uncomment in case null is always being returned
            }
        }
        return vendor;
    }
    




}

