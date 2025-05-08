package com.thefivebros.FinalExam.controller;

import com.thefivebros.FinalExam.model.DoctorDAO;
import com.thefivebros.FinalExam.model.Doctor;
import com.thefivebros.FinalExam.model.Specialty;
import com.thefivebros.fivebros.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/add-doctor")
public class AdminAddDoctor extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userFromSession = (User) session.getAttribute("activeUser");

        if (userFromSession == null || !"active".equals(userFromSession.getStatus()) || !"admin".equals(userFromSession.getPrivileges())) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        List<Specialty> specialties = DoctorDAO.getAllSpecialties();
        req.setAttribute("Specialty", specialties);  // Changed from "doctors" to "Specialty" to match JSP

        req.getRequestDispatcher("WEB-INF/admin-add-doctor.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Session and permission check
        HttpSession session = req.getSession();
        User userFromSession = (User) session.getAttribute("activeUser");
        if (userFromSession == null || !"active".equals(userFromSession.getStatus()) || !"admin".equals(userFromSession.getPrivileges())) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // Get form parameters
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String[] specialties = req.getParameterValues("specialties");

        // Store values for redisplay
        req.setAttribute("firstName", firstName);
        req.setAttribute("lastName", lastName);
        req.setAttribute("selectedSpecialties", specialties);

        // Validation
        boolean validationError = false;
        Doctor doctor = new Doctor();

        // First Name validation
        if (firstName == null || firstName.trim().isEmpty()) {
            validationError = true;
            req.setAttribute("firstNameError", true);
            req.setAttribute("firstNameValid", false);
            req.setAttribute("firstNameMessage", "First name cannot be empty.");
        } else {
            doctor.setFirstName(firstName.trim());
            req.setAttribute("firstNameError", false);
            req.setAttribute("firstNameValid", true);
            req.setAttribute("firstNameMessage", "Looks good!");
        }

        // Last Name validation
        if (lastName == null || lastName.trim().isEmpty()) {
            validationError = true;
            req.setAttribute("lastNameError", true);
            req.setAttribute("lastNameValid", false);
            req.setAttribute("lastNameMessage", "Last name cannot be empty.");
        } else {
            doctor.setLastName(lastName.trim());
            req.setAttribute("lastNameError", false);
            req.setAttribute("lastNameValid", true);
            req.setAttribute("lastNameMessage", "Looks good!");
        }

        // Specialties validation
        if (specialties == null || specialties.length == 0) {
            validationError = true;
            req.setAttribute("specialtiesError", true);
            req.setAttribute("specialtiesValid", false);
            req.setAttribute("specialtiesMessage", "At least one specialty must be selected.");
        } else {
            doctor.setSpecialties(specialties);
            req.setAttribute("specialtiesError", false);
            req.setAttribute("specialtiesValid", true);
            req.setAttribute("specialtiesMessage", "Looks good!");
        }

        // Reload specialties for the form
        req.setAttribute("Specialty", DoctorDAO.getAllSpecialties());

        // Process if no validation errors
        if (!validationError) {
            try {
                boolean doctorAdded = DoctorDAO.addDoctor(doctor);
                req.setAttribute("doctorAdded", doctorAdded);
                if (doctorAdded) {
                    req.setAttribute("doctorAddedMessage", "Successfully added doctor!");
                    // Clear form
                    req.setAttribute("firstName", "");
                    req.setAttribute("lastName", "");
                    req.setAttribute("selectedSpecialties", new String[0]);
                } else {
                    req.setAttribute("doctorAddedMessage", "Error adding doctor.");
                }
            } catch (Exception e) {
                req.setAttribute("doctorAdded", false);
                req.setAttribute("doctorAddedMessage", "Error: " + e.getMessage());
            }
        }

        req.getRequestDispatcher("WEB-INF/admin-add-doctor.jsp").forward(req, resp);
    }
}