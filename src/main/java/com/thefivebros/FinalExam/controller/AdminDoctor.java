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

@WebServlet("/admin-doctor")
public class AdminDoctor extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userFromSession = (User) session.getAttribute("activeUser");
        if (userFromSession == null || !userFromSession.getStatus().equals("active") || !userFromSession.getPrivileges().equals("admin")) {
            session.setAttribute("failureMessageWarning", "Restricted page");
            resp.sendRedirect("login");
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);

            return;


        }
        List<Doctor> doctors = DoctorDAO.getAllDoctors();
        req.setAttribute("doctors", doctors);

        req.setAttribute("pageTitle", "All Specialty");
        req.getRequestDispatcher("WEB-INF/admin-doctor.jsp").forward(req, resp);
    }
}
