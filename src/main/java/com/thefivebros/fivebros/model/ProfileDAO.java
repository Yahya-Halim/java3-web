package com.thefivebros.fivebros.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import static com.thefivebros.shared.MySQL_Connect.getConnection;

public class ProfileDAO {
    public static boolean updateProfile(Profile profile) {
        try (Connection connection = getConnection();
             CallableStatement statement = connection.prepareCall("{CALL sp_update_profile(?,?,?,?,?)}");) {

            statement.setString(1, profile.getAboutMe());
            statement.setString(2, profile.getEmail());
            statement.setString(3, profile.getPhone());
            statement.setString(4, profile.getImage());
            statement.setInt(5, profile.getId());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
