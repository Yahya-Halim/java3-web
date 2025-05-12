package com.thefivebros.FinalExam.controller;

import com.thefivebros.FinalExam.model.Doctor;
import com.thefivebros.FinalExam.model.DoctorDAO;
import com.thefivebros.FinalExam.model.Specialty;
import com.thefivebros.fivebros.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/admin-update-doctor")
public class AdminUpdateDoctor extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userFromSession = (User) session.getAttribute("activeUser");

        if (userFromSession == null || !"active".equals(userFromSession.getStatus()) || !"admin".equals(userFromSession.getPrivileges())) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Access Denied");
            return;
        }

        String doctorIdStr = req.getParameter("doctorId");
        if (doctorIdStr == null || doctorIdStr.trim().isEmpty()) {
            // If no ID, perhaps redirect to the doctor list or show an error
            session.setAttribute("errorMessage", "Doctor ID is required to update.");
            return;
        }

        int doctorId;
        try {
            doctorId = Integer.parseInt(doctorIdStr.trim());
        } catch (NumberFormatException e) {
            session.setAttribute("errorMessage", "Invalid Doctor ID format.");
            resp.sendRedirect(req.getContextPath() + "/admin-doctor");
            return;
        }

        Doctor doctorToUpdate = DoctorDAO.getDoctorById(doctorId);

        if (doctorToUpdate == null) {
            session.setAttribute("errorMessage", "Doctor with ID " + doctorId + " not found.");
            resp.sendRedirect(req.getContextPath() + "/admin-doctor");
            return;
        }

        List<Specialty> allSpecialties = DoctorDAO.getAllSpecialties();

        req.setAttribute("doctor", doctorToUpdate); // Set the doctor object to be updated
        req.setAttribute("Specialty", allSpecialties); // Set all available specialties for the form

        req.getRequestDispatcher("/WEB-INF/admin-update-doctor.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userFromSession = (User) session.getAttribute("activeUser");

        if (userFromSession == null || !"active".equals(userFromSession.getStatus()) || !"admin".equals(userFromSession.getPrivileges())) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Access Denied");
            return;
        }

        String doctorIdStr = req.getParameter("doctorId");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName"); // Note: DoctorDAO.updateDoctor doesn't update lastName with current sp_update_doctor_with_specialties
        String[] submittedSpecialties = req.getParameterValues("specialties");

        Doctor doctor = new Doctor(); // This object will hold submitted data

        // Validate and set Doctor ID
        if (doctorIdStr == null || doctorIdStr.trim().isEmpty()) {
            // This case should ideally be caught by doGet or client-side, but good to have server-side check
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Doctor ID is missing.");
            return;
        }
        try {
            doctor.setId(Integer.parseInt(doctorIdStr.trim()));
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Doctor ID format.");
            return;
        }

        // Set submitted values to the doctor object for validation and potential re-display
        doctor.setFirstName(firstName);
        doctor.setLastName(lastName); // lastName will be set on the object, but sp_update_doctor_with_specialties might not use it
        doctor.setSpecialties(submittedSpecialties == null ? new String[0] : submittedSpecialties);

        // Validation logic
        boolean validationError = false;

        // First Name validation
        if (firstName == null || firstName.trim().isEmpty()) {
            validationError = true;
            req.setAttribute("firstNameError", true);
            req.setAttribute("firstNameMessage", "First name cannot be empty.");
        } else {
            doctor.setFirstName(firstName.trim()); // Ensure trimmed version is on object
            req.setAttribute("firstNameError", false);
            req.setAttribute("firstNameValid", true);
            req.setAttribute("firstNameMessage", "Looks good!");
        }


        if (lastName == null || lastName.trim().isEmpty()) {
            validationError = true;
            req.setAttribute("lastNameError", true);
            req.setAttribute("lastNameMessage", "Last name cannot be empty.");
        } else {
            doctor.setLastName(lastName.trim()); // Ensure trimmed version is on object
            req.setAttribute("lastNameError", false);
            req.setAttribute("lastNameValid", true);
            req.setAttribute("lastNameMessage", "Looks good!");
        }

        if (submittedSpecialties == null || submittedSpecialties.length == 0) {
            validationError = true;
            req.setAttribute("specialtiesError", true);
            req.setAttribute("specialtiesMessage", "At least one specialty must be selected.");
        } else {
            req.setAttribute("specialtiesError", false);
            req.setAttribute("specialtiesValid", true);
            req.setAttribute("specialtiesMessage", "Looks good!");
        }

        List<Specialty> allSpecialtiesList = DoctorDAO.getAllSpecialties();
        req.setAttribute("specialty", allSpecialtiesList);


        req.setAttribute("doctor", doctor);


        if (validationError) {
            req.setAttribute("formError", true); // General form error flag
            req.setAttribute("formMessage", "Please correct the errors highlighted below.");
            req.getRequestDispatcher("/WEB-INF/admin-update-doctor.jsp").forward(req, resp);
            return;
        }

        try {

            boolean doctorUpdated = DoctorDAO.updateDoctor(doctor);

            if (doctorUpdated) {
                session.setAttribute("successMessage", "Doctor updated successfully!");
                resp.sendRedirect(req.getContextPath() + "/admin-doctor"); // Redirect to a doctor list page
            } else {
                // Fetch the original doctor data again for the form if update fails but wasn't a validation error
                Doctor originalDoctor = DoctorDAO.getDoctorById(doctor.getId());
                req.setAttribute("doctor", originalDoctor != null ? originalDoctor : doctor); // Prefer original if exists

                req.setAttribute("formError", true);
                req.setAttribute("formMessage", "Failed to update doctor. The doctor might not exist or an internal error occurred.");
                req.getRequestDispatcher("/WEB-INF/admin-update-doctor.jsp").forward(req, resp);
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            Doctor originalDoctor = DoctorDAO.getDoctorById(doctor.getId());
            req.setAttribute("doctor", originalDoctor != null ? originalDoctor : doctor); // Prefer original if exists

            req.setAttribute("formError", true);
            req.setAttribute("formMessage", "Error updating doctor: " + e.getMessage());
            req.getRequestDispatcher("/WEB-INF/admin-update-doctor.jsp").forward(req, resp);
        }
    }
}