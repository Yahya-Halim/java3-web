package com.thefivebros.FinalExam.controller;


import com.thefivebros.FinalExam.model.Doctor;
import com.thefivebros.FinalExam.model.DoctorDAO;
import com.thefivebros.fivebros.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin-delete-doctor")
public class AdminDeleteDoctor extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userFromSession = (User) session.getAttribute("activeUser");
        if (userFromSession == null || !"active".equals(userFromSession.getStatus()) || !"admin".equals(userFromSession.getPrivileges())) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Access Denied");
            return;
        }
        boolean doctors = DoctorDAO.deleteDoctor(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("doctors", doctors);
        session.setAttribute("warningMessage", "Deletion should be performed using a POST request (e.g., via a form button).");
        resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + "/admin-doctor"));

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

        if (doctorIdStr == null || doctorIdStr.trim().isEmpty()) {
            session.setAttribute("errorMessage", "Doctor ID is required for deletion.");
            resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + "/admin-doctor"));
            return;
        }

        try {
            int doctorId = Integer.parseInt(doctorIdStr.trim());
            boolean deleted = DoctorDAO.deleteDoctor(doctorId);

            if (deleted) {
                session.setAttribute("successMessage", "Doctor deleted successfully.");
            } else {
                session.setAttribute("errorMessage", "Failed to delete doctor. The doctor might not exist or an internal error occurred.");
            }
        } catch (NumberFormatException e) {
            session.setAttribute("errorMessage", "Invalid Doctor ID format.");
        } catch (RuntimeException e) {
            e.printStackTrace(); // Log error
            session.setAttribute("errorMessage", "Error deleting doctor: " + e.getMessage());
        }

        resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + "/admin-doctor"));
    }
}
