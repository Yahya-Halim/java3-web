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
        String errorMsg = "<strong>Error code:</strong> " + req.getAttribute(ERROR_STATUS_CODE) + "<br>";
        errorMsg += "<strong>Exception:</strong> " + req.getAttribute(ERROR_EXCEPTION_TYPE) + "<br>";
        errorMsg += "<strong>Message:</strong> " + req.getAttribute(ERROR_MESSAGE) + "<br>";
        errorMsg += "<strong>Servlet:</strong> " + req.getAttribute(ERROR_SERVLET_NAME) + "<br>";
        errorMsg += "<strong>Request URI:</strong> " + req.getAttribute(ERROR_REQUEST_URI) + "<br>";

//        boolean isDebugging = Boolean.parseBoolean(getServletContext().getInitParameter("debugging"));
//        if (!isDebugging) {
//            String bodyHTML = String.format("""
//                    <html>
//                        <body>
//                            <h2>Error Notification</h2>
//                            <p>%s</p>
//                        </body>
//                    </html>
//                    """, errorMsg);
//            String toEmailAddress = Config.getEnv("ADMIN_EMAIL");
//            String subject = "Error Notification";
//            EmailThread emailThread1 = new EmailThread(toEmailAddress, subject, bodyHTML);
//            emailThread1.start();
//            try {
//                emailThread1.join();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            errorMsg = emailThread1.getErrorMessage();
//            if (errorMsg.isEmpty()) {
//                errorMsg = "A notification has been sent to the administrator";
//            }
//        }


        req.setAttribute("errorMsg", errorMsg);
        req.setAttribute("pageTitle", "Error");
        req.getRequestDispatcher("WEB-INF/error.jsp").forward(req, resp);
    }
}
