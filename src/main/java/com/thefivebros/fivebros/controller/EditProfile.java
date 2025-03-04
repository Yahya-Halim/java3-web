package com.thefivebros.fivebros.controller;

import com.thefivebros.fivebros.model.User;
import com.thefivebros.fivebros.model.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/edit-profile")
public class EditProfile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("activeUser");
        if(user == null) {
            session.setAttribute("flashMessageWarning","You must be logged in to edit your profile.");
            resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + "/login?redirect=edit-profile"));
            return;

        } else if(user != null && !user.getStatus().equals("active")){
            session.setAttribute("flashMessageDanger","Your account is locked or inactive.");
            resp.sendRedirect(resp.encodeRedirectURL("/"));
            return;


        }
        req.setAttribute("pageTitle", "Edit Profile");
        req.getRequestDispatcher("WEB-INF/edit-profile.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String language = req.getParameter("language");
        req.setAttribute("email", email);
        req.setAttribute("phone", phone);

        HttpSession session = req.getSession();
        User activeUser = (User)session.getAttribute("activeUser");


        req.setAttribute("pageTitle", "Edit Profile");
        req.getRequestDispatcher("WEB-INF/edit-profile.jsp").forward(req, resp);
        boolean errorFound = false;
        try {
            if(!firstName.equals(activeUser.getFirstName())) {
                activeUser.setFirstName(firstName);
            }
        } catch(IllegalArgumentException e) {
            errorFound = true;
            req.setAttribute("firstNameError", e.getMessage());
        }

        try {
            if(!lastName.equals(activeUser.getLastName())) {
                activeUser.setLastName(lastName);
            }
        } catch(IllegalArgumentException e) {
            errorFound = true;
            req.setAttribute("lastNameError", e.getMessage());
        }

        if(email != null && !email.equals("") && !email.equals(activeUser.getEmail()) && UserDAO.get(email) != null) {
            errorFound = true;
            req.setAttribute("emailError", "A user with that email already exists.");
        } else {
            try {
                activeUser.setEmail(email);
            } catch(IllegalArgumentException e) {
                req.setAttribute("emailError", e.getMessage());
            }
        }

        try {
            if(phone != null && !phone.equals(activeUser.getPhone())) {
                activeUser.setPhone(phone);
            }
        } catch(IllegalArgumentException e) {
            errorFound = true;
            req.setAttribute("phoneError", e.getMessage());
        }

        try {
            if(!language.equals(activeUser.getLanguage())) {
                activeUser.setLanguage(language);
            }
        } catch(IllegalArgumentException e) {
            errorFound = true;
            req.setAttribute("languageError", e.getMessage());
        }
        if (!errorFound) {
            UserDAO.update(activeUser);
            session.setAttribute("activeUser", activeUser);
            session.setAttribute("flashMessageSuccess", "Your profile was updated");
        } else {
            session.setAttribute("flashMessageWarning", "Your profile was not updated");
        }

        req.setAttribute("pageTitle", "Edit Profile");
        req.getRequestDispatcher("WEB-INF/edit-profile.jsp").forward(req, resp);
    }

}
