package com.thefivebros.fivebros.controller;

import com.thefivebros.fivebros.model.Plan;
import com.thefivebros.fivebros.model.PlanDAO;
import com.thefivebros.fivebros.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin-plans")
public class AdminPlan extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userFromSession = (User) session.getAttribute("activeUser");

        if (userFromSession == null || !"active".equals(userFromSession.getStatus()) || !"admin".equals(userFromSession.getPrivileges())) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        List<Plan> plans = PlanDAO.getAll();
        req.setAttribute("plans", plans);
        req.getRequestDispatcher("WEB-INF/admin-plans.jsp").forward(req, resp);
    }
}
