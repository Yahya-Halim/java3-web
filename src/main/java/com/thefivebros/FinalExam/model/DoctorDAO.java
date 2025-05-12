package com.thefivebros.FinalExam.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import static com.thefivebros.shared.MySQL_Connect.getConnection;

public class DoctorDAO {

    public static ArrayList<Doctor> getAllDoctors() {
        ArrayList<Doctor> doctors = new ArrayList<>();
        try (Connection connection = getConnection();
             CallableStatement cstmt = connection.prepareCall("{call sp_get_doctors_with_specialties()}");
             ResultSet rs = cstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("doctor_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");

                // Handle potential null value for specialties
                String specialtiesString = rs.getString("specialties");
                String[] specialties = (specialtiesString != null) ? specialtiesString.split(",") : new String[0];

                // Create a Doctor object and add it to the list
                Doctor doctor = new Doctor(id, firstName, lastName, specialties);
                doctors.add(doctor);
            }

        }catch (SQLException e) {
            throw new RuntimeException(e);

        }

        return doctors;
    }
    public static ArrayList<Specialty> getAllSpecialties() {
        ArrayList<Specialty> specialties = new ArrayList<>();
        try (Connection connection = getConnection()) {
            CallableStatement cstmt = connection.prepareCall("{call sp_get_specialties()}");
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Specialty specialty = new Specialty(id, name);
                specialties.add(specialty);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return specialties;
    }
    public static boolean addDoctor(Doctor doctor) {
        try (Connection connection = getConnection()) {
            String specialtiesJson = "[\"" + String.join("\",\"", doctor.getSpecialties()) + "\"]";

            CallableStatement cstmt = connection.prepareCall("{call sp_add_doctor_with_specialties(?,?,?)}");
            cstmt.setString(1, doctor.getFirstName());
            cstmt.setString(2, doctor.getLastName());
            cstmt.setString(3, specialtiesJson);

            int rowsAffected = cstmt.executeUpdate();
            return rowsAffected == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    // make the update doctor in the DoctorDAO class
    public static boolean updateDoctor(Doctor doctorWithNewState) {
        Doctor oldDoctorState = getDoctorById(doctorWithNewState.getId());
        if (oldDoctorState == null) {
            throw new RuntimeException("Update failed: Doctor with ID " + doctorWithNewState.getId() + " not found.");
        }
        String oldFirstName = oldDoctorState.getFirstName();
        String oldLastName = oldDoctorState.getLastName();
        try (Connection connection = getConnection()) {
            String[] newSpecialtyIds =  doctorWithNewState.getSpecialties();
            String newSpecialtiesJson;
            if (newSpecialtyIds == null || newSpecialtyIds.length == 0) {
                newSpecialtiesJson = "[]";
            } else {
                newSpecialtiesJson = "[\"" + String.join("\",\"", newSpecialtyIds) + "\"]";
            }
            CallableStatement cstmt = connection.prepareCall("{call sp_update_doctor_with_specialties(?,?,?,?,?,?)}");

            cstmt.setInt(1, doctorWithNewState.getId());
            cstmt.setString(2, oldFirstName);
            cstmt.setString(3, oldLastName);
            cstmt.setString(4, doctorWithNewState.getFirstName());
            cstmt.setString(5, doctorWithNewState.getLastName());
            cstmt.setString(6, newSpecialtiesJson);

            int rowsAffected = cstmt.executeUpdate();
            return rowsAffected == 1;
        } catch (SQLException e) {
            throw new RuntimeException("Error updating doctor: " + e.getMessage(), e);
        }
    }

    // make the delete doctor in the DoctorDAO class
    public static boolean deleteDoctor(int id) {
        try (Connection connection = getConnection()) {
            CallableStatement cstmt = connection.prepareCall("{call sp_delete_doctor_with_specialties(?)}");
            cstmt.setInt(1, id);

            int rowsAffected = cstmt.executeUpdate();
            return rowsAffected == 1;
            } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Doctor getDoctorById(int id) {
        Doctor doctor = null;
        try (Connection connection = getConnection();
             CallableStatement cstmt = connection.prepareCall("{call sp_get_doctor_by_id(?)}")) {
            cstmt.setInt(1, id);
            ResultSet rs = cstmt.executeQuery();

            if (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String specialtiesString = rs.getString("specialties");
                String[] specialties = (specialtiesString != null) ? specialtiesString.split(", ") : new String[0];
                doctor = new Doctor(id, firstName, lastName, specialties);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return doctor;
    }







}


