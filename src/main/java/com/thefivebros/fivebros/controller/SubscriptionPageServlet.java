package com.thefivebros.fivebros.controller;

import com.thefivebros.fivebros.model.Plan;
import com.thefivebros.fivebros.model.User;
import com.thefivebros.fivebros.model.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

@WebServlet("/subscription")
public class SubscriptionPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String plan = request.getParameter("plan");
        request.setAttribute("plan", plan);

        List<Plan> plans = UserDAO.getPlans(); // get all available plans
        request.setAttribute("plans", plans);

        request.getRequestDispatcher("/WEB-INF/subscription.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("activeUser");

        // You probably want to store user update logic here
        if (user != null) {
            // Update the user with chosen plan (e.g. premium)
            String plan = req.getParameter("plan");

            // Optionally store it in DB here...
            // user.setPlan(plan);
            // UserDAO.update(user);

            user.setCreatedAt(Instant.now());
            session.setAttribute("activeUser", user);
            session.setAttribute("flashMessageSuccess", "Subscription plan selected");

            if ("premium".equalsIgnoreCase(plan)) {
                resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + "/ecommerce/checkout"));
            } else {
                resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + "/"));
            }
        } else {
            session.setAttribute("flashMessageError", "No active user session found. Please sign up or log in.");
            resp.sendRedirect(req.getContextPath() + "/signup");
        }
    }
}
