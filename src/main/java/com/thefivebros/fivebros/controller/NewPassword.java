package com.thefivebros.fivebros.controller;

import com.thefivebros.fivebros.model.User;
import com.thefivebros.fivebros.model.UserDAO;
import com.thefivebros.shared.EmailThread;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/new-password")
public class NewPassword extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getParameter("token");
        req.setAttribute("token", token);
        req.setAttribute("pageTitle", "New password");
        req.getRequestDispatcher("WEB-INF/new-password.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password1 = req.getParameter("password1");
        String password2 = req.getParameter("password2");
        String token = req.getParameter("token");
        req.setAttribute("password1", password1);
        req.setAttribute("password2", password2);
        req.setAttribute("token", token);
        User userPassword = new User();
        boolean errorFound = false;

        try {
            userPassword.setPassword(password1.toCharArray());
        } catch(IllegalArgumentException e) {
            errorFound = true;
            req.setAttribute("password1Error", e.getMessage());
        }
        if(password2 != null && password2.equals("")) {
            errorFound = true;
            req.setAttribute("password2Error", "Please confirm your password");
        }
        if(password1 != null && password2 != null && !password2.equals(password1)) {
            errorFound = true;
            req.setAttribute("password2Error", "Passwords don't match");
        }
        if(token == null) {
            req.setAttribute("newPasswordFail", "Invalid or missing token");
        }

        if (!errorFound) {
            String email = UserDAO.getPasswordReset(token);
            if (email == null || email.equals("")) {
                req.setAttribute("newPasswordFail", "Token not found.");
            } else {
                boolean passwordUpdated = UserDAO.updatePassword(email, password1);
                if (passwordUpdated) {
                    // Unlock the account by setting the status to "active"
                    User user = UserDAO.get(email); // Retrieve the user by email
                    if (user != null) {
                        user.setStatus("active");
                        boolean isUpdated = UserDAO.userUpdate(email, user);
                        if (!isUpdated) {
                            req.setAttribute("newPasswordFail", "An error occurred while unlocking your account. Please contact support.");
                            req.getRequestDispatcher("WEB-INF/new-password.jsp").forward(req, resp);
                            return;
                        }
                    }

                    // Send email notification
                    String subject = "New Password Created";
                    String message = "<h2>Your new password is created<h2>";
                    message += "<p>Your password has changed. If you suspect that someone else changed your password, please reset it with this link:</p>";
                    String appURL = req.isSecure() ? req.getServletContext().getInitParameter("appURLCloud") : req.getServletContext().getInitParameter("appURLLocal");
                    String fullURL = String.format("%s/reset-password", appURL);
                    message += String.format("<p><a href=\"%s\" target=\"_blank\">%s</a></p>", fullURL, fullURL);
                    message += "<p>If you did not request to reset your password, you can ignore this message and your password will not be changed.</p>";

                    EmailThread emailThread = new EmailThread(email, subject, message);
                    emailThread.start();
                    try {
                        emailThread.join();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    // Redirect to login page with success message
                    HttpSession session = req.getSession();
                    session.setAttribute("flashMessageSuccess", "New password has been created. Please sign in.");
                    resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + "/login"));
                    return;
                } else {
                    req.setAttribute("newPasswordFail", "Failed to update password. Please try again.");
                }
            }
        }

        req.setAttribute("pageTitle", "New password");
        req.getRequestDispatcher("WEB-INF/new-password.jsp").forward(req, resp);
    }

}

