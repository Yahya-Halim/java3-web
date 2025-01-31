package com.thefivebros.fivebros.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import static jakarta.servlet.RequestDispatcher.*;
import java.io.IOException;

@WebServlet("/errorHandler")
public class ErrorHandler extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String errorMsg = "<strong>";
        req.setAttribute("pageTitle", "Error");
        req.getRequestDispatcher("WEB-INF/error.jsp").forward(req, resp);
    }
}
