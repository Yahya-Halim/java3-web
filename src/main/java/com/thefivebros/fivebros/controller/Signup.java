package com.thefivebros.fivebros.controller;

import com.thefivebros.fivebros.model.User;
import com.thefivebros.fivebros.model.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@WebServlet("/signup")
public class Signup extends HttpServlet {
    private static final String RECAPTCHA_SECRET_KEY = "6LfVodsqAAAAAIhC0SaMiUyrZn0ZZGRbDB3HoI9c";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageTitle", "Sign up for an account");
        req.getRequestDispatcher("/WEB-INF/signup.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password1 = req.getParameter("password1");
        String password2 = req.getParameter("password2");
        String[] terms = req.getParameterValues("terms");
        String recaptchaResponse = req.getParameter("g-recaptcha-response");

        req.setAttribute("email", email);
        req.setAttribute("password1", password1);
        req.setAttribute("password2", password2);
        req.setAttribute("terms", (terms != null && terms[0].equals("agree")) ? "agree" : "");
        req.setAttribute("pageTitle", "Sign up for an account");

        boolean errorFound = false;

        // Verify reCAPTCHA response
        if (recaptchaResponse == null || !isCaptchaValid(RECAPTCHA_SECRET_KEY, recaptchaResponse)) {
            errorFound = true;
            req.setAttribute("userAddFail", "reCAPTCHA verification failed. Please try again.");
        }

        User user = new User();
        try {
            user.setEmail(email);
        } catch (IllegalArgumentException e) {
            errorFound = true;
            req.setAttribute("emailError", e.getMessage());
        }
        if (UserDAO.get(email) != null) {
            errorFound = true;
            req.setAttribute("emailError", "A user with that email already exists. Please login or reset your password.");
        }
        try {
            user.setPassword(password1.toCharArray());
        } catch (IllegalArgumentException e) {
            errorFound = true;
            req.setAttribute("password1Error", e.getMessage());
        }
        if (password2 == null || password2.equals("")) {
            errorFound = true;
            req.setAttribute("password2Error", "Please confirm your password");
        }
        if (password1 != null && password2 != null && !password2.equals(password1)) {
            errorFound = true;
            req.setAttribute("password2Error", "Passwords don't match");
        }
        if (terms == null || !terms[0].equals("agree")) {
            errorFound = true;
            req.setAttribute("termsError", "You must agree to our terms of use");
        }

        if (!errorFound) {
            user.setPrivileges("user");
            user.setStatus("active");
            boolean userAdded = false;
            try {
                userAdded = UserDAO.add(user);
            } catch (RuntimeException e) {
                req.setAttribute("userAddFail", "User could not be added");
            }
            if (userAdded) {
                user.setPassword(null);
                HttpSession session = req.getSession();
                session.invalidate();
                session = req.getSession();
                session.setAttribute("activeUser", user);
                session.setAttribute("flashMessageSuccess", "User successfully added");
                resp.sendRedirect(req.getContextPath());
                return;
            }
        }

        req.setAttribute("pageTitle", "Sign up for an account");
        req.getRequestDispatcher("/WEB-INF/signup.jsp").forward(req, resp);
    }

    /**
     * Validates Google reCAPTCHA V2 or Invisible reCAPTCHA.
     *
     * @param secretKey Secret key (key given for communication between your site and Google)
     * @param response reCAPTCHA response from client side.
     * @return true if validation successful, false otherwise.
     */
    public synchronized boolean isCaptchaValid(String secretKey, String response) {
        try {
            String url = "https://www.google.com/recaptcha/api/siteverify";
            String params = "secret=" + secretKey + "&response=" + response;

            HttpURLConnection http = (HttpURLConnection) new URL(url).openConnection();
            http.setDoOutput(true);
            http.setRequestMethod("POST");
            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            try (OutputStream out = http.getOutputStream()) {
                out.write(params.getBytes("UTF-8"));
            }

            try (InputStream res = http.getInputStream();
                 BufferedReader rd = new BufferedReader(new InputStreamReader(res, "UTF-8"))) {
                StringBuilder sb = new StringBuilder();
                int cp;
                while ((cp = rd.read()) != -1) {
                    sb.append((char) cp);
                }
                JSONObject json = new JSONObject(sb.toString());
                return json.getBoolean("success");
            }
        } catch (Exception e) {
            return false;
        }
    }
}
