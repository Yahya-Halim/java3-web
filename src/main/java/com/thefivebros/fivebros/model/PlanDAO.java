package com.thefivebros.fivebros.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

import static com.thefivebros.shared.MySQL_Connect.getConnection;

public class PlanDAO {

    public static List<Plan> getAll() {
        List<Plan> plans = new ArrayList<>();
        try (Connection conn = getConnection();
             CallableStatement stmt = conn.prepareCall("{CALL sp_get_all_plans()}")) {
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
            throw new RuntimeException(e);
        }
        return plans;
    }

    public static Plan get(int id) {
        Plan plan = null;
        try (Connection conn = getConnection();
             CallableStatement stmt = conn.prepareCall("{CALL sp_get_plan(?)}")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                plan = new Plan(
                        rs.getInt("plan_id"),
                        rs.getString("name"),
                        rs.getBigDecimal("price"),
                        rs.getString("description")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return plan;
    }

    public static boolean add(Plan plan) {
        try (Connection conn = getConnection();
             CallableStatement stmt = conn.prepareCall("{CALL sp_add_plan(?,?,?)}")) {
            stmt.setString(1, plan.getName());
            stmt.setBigDecimal(2, plan.getPrice());
            stmt.setString(3, plan.getDescription());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean update(Plan plan) {
        try (Connection conn = getConnection();
             CallableStatement stmt = conn.prepareCall("{CALL sp_update_plan(?,?,?,?)}")) {
            stmt.setInt(1, plan.getId());
            stmt.setString(2, plan.getName());
            stmt.setBigDecimal(3, plan.getPrice());
            stmt.setString(4, plan.getDescription());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean delete(int id) {
        try (Connection conn = getConnection();
             CallableStatement stmt = conn.prepareCall("{CALL sp_delete_plan(?)}")) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
