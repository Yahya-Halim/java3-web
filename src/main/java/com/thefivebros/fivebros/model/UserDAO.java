package com.thefivebros.fivebros.model;

import com.thefivebros.shared.EmailThread;
import jakarta.servlet.http.HttpServletRequest;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.thefivebros.shared.MySQL_Connect.getConnection;


public class UserDAO {
    public static void main(String[] args) {
//        getAll().forEach(System.out::println);
        System.out.println(get("yahyamohamed11no1@gmail.com"));
//        User user = new User();
//        user.setEmail("yoyo@test.com");
//        user.setPassword("P@ssw0rd".toCharArray());
//        add(user);

    }


    public static void deletePasswordReset(String email) {
        try (Connection connection = getConnection();
             CallableStatement statement = connection.prepareCall("{CALL sp_delete_password_reset(?)}")) {
            statement.setString(1, email);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }






    public static String getPasswordReset(String token) {
        String email = "";
        try (Connection connection = getConnection();
             CallableStatement statement = connection.prepareCall("{CALL sp_get_password_reset(?)}")) {
            statement.setString(1, token);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Instant now = Instant.now();
                Instant created_at = resultSet.getTimestamp("created_at").toInstant();
                Duration duration = Duration.between(created_at, now);
                long minutesElapsed = duration.toMinutes();
                if(minutesElapsed < 30) {
                    email = resultSet.getString("email");
                }
                int id = resultSet.getInt("id");
                CallableStatement statement2 = connection.prepareCall("{CALL sp_delete_password_reset(?)}");
                statement2.setInt(1, id);
                statement2.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return email;
    }
    public static boolean updatePassword(String email, String password) {
        try (Connection connection = getConnection()) {
            if (connection != null) {
                try (CallableStatement statement = connection.prepareCall("{CALL sp_update_user_password(?, ?)}")) {
                    statement.setString(1, email);
                    String encryptedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
                    statement.setString(2, encryptedPassword);
                    int rowsAffected = statement.executeUpdate();
                    return rowsAffected == 1;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }





    public static String passwordReset(String email, HttpServletRequest req) {
        User user = get(email);
        if (user == null) {
            return "No user found that matches that email";
        } else {
            try (Connection connection = getConnection()) {
                String uuid = String.valueOf(UUID.randomUUID());
                CallableStatement statement = connection.prepareCall("{call sp_add_password_reset(?,?)}");
                statement.setString(1, email);
                statement.setString(2, uuid);
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    // Generate the email content
                    String subject = "Reset Password";
                    String appURL = req.isSecure() ?
                            req.getServletContext().getInitParameter("appURLCloud") :
                            req.getServletContext().getInitParameter("appURLLocal");
                    String fullURL = String.format("%s/new-password?token=%s", appURL, uuid);

                    // Create the email body using the tech-themed template
                    String bodyContent = "<h2 style='color: #00ffcc; font-family: \"Courier New\", monospace;'>Reset Password</h2>" +
                            "<p style='color: #ffffff; font-family: Arial, sans-serif;'>Please click this link to securely reset your password. This link expires in 30 minutes.</p>" +
                            String.format("<p style='color: #ffffff; font-family: Arial, sans-serif;'><a href=\"%s\" target=\"_blank\" style='color: #00ffcc; text-decoration: none;'>%s</a></p>", fullURL, fullURL) +
                            "<p style='color: #ffffff; font-family: Arial, sans-serif;'>If you did not request to reset your password, you can ignore this message and your password will not be changed.</p>";

                    String htmlContent = "<html>" +
                            "<head><style>" +
                            "body { font-family: Arial, sans-serif; background-color: #1a1a1a; color: #ffffff; margin: 0; padding: 0; }" +
                            ".container { max-width: 600px; margin: 0 auto; padding: 20px; background-color: #2a2a2a; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 255, 204, 0.3); }" +
                            ".header { font-size: 24px; font-weight: bold; color: #00ffcc; font-family: \"Courier New\", monospace; text-align: center; }" +
                            ".body { margin-top: 20px; }" +
                            ".footer { margin-top: 20px; font-size: 12px; color: #777; text-align: center; }" +
                            "a { color: #00ffcc; text-decoration: none; }" +
                            "a:hover { text-decoration: underline; }" +
                            "</style></head>" +
                            "<body>" +
                            "<div class='container'>" +
                            "<div class='header'>Reset Password</div>" +
                            "<div class='body'>" + bodyContent + "</div>" +
                            "<div class='footer'>This email was sent by The Five Bro's. Please do not reply to this email.</div>" +
                            "</div>" +
                            "</body>" +
                            "</html>";

                    // Send the email
                    EmailThread emailThread = new EmailThread(email, subject, htmlContent);
                    emailThread.start();
                    try {
                        emailThread.join();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    String errorMessage = emailThread.getErrorMessage();
                    if (errorMessage == null || errorMessage.isEmpty()) {
                        return "If there's an account associated with the email entered, we will send a password reset link.";
                    } else {
                        return errorMessage;
                    }
                } else {
                    return "Sorry, we couldn't process your password reset. Try again.";
                }
            } catch (SQLException e) {
                return "SQLException " + e.getMessage();
            }
        }
    }

    public static List<User> getAll() {
        List<User> list = new ArrayList<>();
        try (Connection connection = getConnection()) {
            CallableStatement cstmt = connection.prepareCall("{call sp_get_all_users()}");
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("user_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                char[] password = rs.getString("password").toCharArray();
                String language = rs.getString("language");
                String status = rs.getString("status");
                String privileges = rs.getString("privileges");
                Instant createdAt = rs.getTimestamp("created_at").toInstant();
                String timezone = rs.getString("timezone");
                User user = new User(userId, firstName, lastName, email, phone, password, language, status, privileges, createdAt, timezone);
                list.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static User get(String email) {
        User user = null;
        try(Connection connection = getConnection()) {
            CallableStatement cstmt = connection.prepareCall("{call sp_get_user(?)}");
            cstmt.setString(1, email);
            ResultSet rs = cstmt.executeQuery();
            if (rs.next()) {
                int userId = rs.getInt("user_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String phone = rs.getString("phone");
                char[] password = rs.getString("password").toCharArray();
                String language = rs.getString("language");
                String status = rs.getString("status");
                String privileges = rs.getString("privileges");
                Instant createdAt = rs.getTimestamp("created_at").toInstant();
                String timezone = rs.getString("timezone");
                user = new User(userId, firstName, lastName, email, phone, password, language, status, privileges, createdAt, timezone);
            }
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
    public static boolean add(User user) {
        try(Connection connection = getConnection();
            CallableStatement statement = connection.prepareCall("{CALL sp_add_user(?,?,?,?)}");
        ) {
            statement.setString(1, user.getEmail());
            statement.setString(2, BCrypt.hashpw(String.valueOf(user.getPassword()), BCrypt.gensalt(12)));
            statement.setString(3, user.getStatus());
            statement.setString(4, user.getPrivileges());
            int rowsAdded = statement.executeUpdate();
            return rowsAdded == 1;
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean update(User user) {
        String sql = "{CALL sp_update_user(?,?,?,?,?,?,?,?,?)}";

        try (Connection conn = getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {

            // Set the parameters for the stored procedure
            cstmt.setInt(1, user.getUserId());
            cstmt.setString(2, user.getFirstName());
            cstmt.setString(3, user.getLastName());
            cstmt.setString(4, user.getEmail());
            cstmt.setString(5, user.getPhone());
            cstmt.setString(6, new String(user.getPassword()));
            cstmt.setString(7, user.getLanguage());
            cstmt.setString(8, user.getStatus());
            cstmt.setString(9, user.getPrivileges());
            cstmt.setString(10, user.getTimezone());

            // Execute the stored procedure
            int rowsAffected = cstmt.executeUpdate();
            return rowsAffected == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
    public static boolean userUpdate(String originalEmail, User newUser) {
        User existingUser = get(originalEmail);
        try(Connection connection = getConnection();
            CallableStatement statement = connection.prepareCall("{CALL sp_update_user(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}")
        ) {
            statement.setInt(1, existingUser.getUserId());
            statement.setString(2, existingUser.getFirstName());
            statement.setString(3, existingUser.getLastName());
            statement.setString(4, existingUser.getEmail());
            statement.setString(5, existingUser.getPhone());
            statement.setString(6, existingUser.getLanguage());
            statement.setString(7, existingUser.getStatus());
            statement.setString(8, existingUser.getPrivileges());
            statement.setString(9, existingUser.getTimezone());
            statement.setString(10, newUser.getFirstName());
            statement.setString(11, newUser.getLastName());
            statement.setString(12, newUser.getEmail());
            statement.setString(13, newUser.getPhone());
            statement.setString(14, newUser.getLanguage());
            statement.setString(15, newUser.getStatus());
            statement.setString(16, newUser.getPrivileges());
            statement.setString(17, newUser.getTimezone());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public static List<Plan> getPlans() {
        List<Plan> plans = new ArrayList<>();
        try (Connection conn = getConnection();
             CallableStatement stmt = conn.prepareCall("{CALL sp_get_plans()}")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Plan plan = new Plan(
                        rs.getInt("plan_id"),
                        rs.getString("name"),
                        rs.getBigDecimal("price"),
                        rs.getString("description")
                );
                plans.add(plan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plans;
    }

    public static void insertBlog(int userId, String postContent) {
        String sql = "{CALL sp_insert_blog(?, ?)}";

        try (Connection conn = getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, userId);
            stmt.setString(2, postContent);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean delete(User user) {
        try(Connection connection = getConnection()) {
            CallableStatement statement = connection.prepareCall("{CALL sp_delete_user(?)}");
            statement.setInt(1, user.getUserId());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

