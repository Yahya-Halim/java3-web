package com.thefivebros.fivebros.controller;

import com.thefivebros.fivebros.model.PlanDAO;
import com.thefivebros.fivebros.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/delete-plan")
public class AdminDeletePlan extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userFromSession = (User) session.getAttribute("activeUser");

        // Check if admin is logged in and active
        if (userFromSession == null || !"active".equals(userFromSession.getStatus()) || !"admin".equals(userFromSession.getPrivileges())) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        String idParam = req.getParameter("id");
        int id;

        try {
            id = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid plan ID.");
            return;
        }

        boolean success = PlanDAO.delete(id);

        if (success) {
            session.setAttribute("planDeleteMessage", "Plan deleted successfully.");
        } else {
            session.setAttribute("planDeleteMessage", "Failed to delete the plan.");
        }

        // Redirect back to plan list page
        resp.sendRedirect("admin-plans");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userFromSession = (User) session.getAttribute("activeUser");

        // Permission check
        if (userFromSession == null || !"active".equals(userFromSession.getStatus()) || !"admin".equals(userFromSession.getPrivileges())) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        String idParam = req.getParameter("id");
        int id;

        try {
            id = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid plan ID.");
            return;
        }

        boolean success = PlanDAO.delete(id);

        if (success) {
            session.setAttribute("planDeleteMessage", "Plan deleted successfully.");
        } else {
            session.setAttribute("planDeleteMessage", "Failed to delete the plan.");
        }

        resp.sendRedirect("admin-plans"); // Redirect to plan list page
    }
}
