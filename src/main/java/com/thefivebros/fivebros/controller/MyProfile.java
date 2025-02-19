package com.thefivebros.fivebros.controller;


import com.thefivebros.fivebros.model.Profile;
import com.thefivebros.fivebros.model.ProfileDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;


import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@WebServlet("/profile")
public class MyProfile extends HttpServlet {




    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageTitle", "Profile");
        req.getRequestDispatcher("WEB-INF/profile.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve form fields
        int userId = Integer.parseInt(req.getParameter("id")); // Assuming 'id' is passed in the form
        String aboutMe = req.getParameter("aboutMe");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");

        // Handle image upload
        Part filePart = req.getPart("image");
        String image = null;

        if (filePart != null && filePart.getSize() > 0) {
            String uploadDir = getServletContext().getRealPath("") + File.separator + "uploads";
            File uploadDirFile = new File(uploadDir);
            if (!uploadDirFile.exists()) {
                uploadDirFile.mkdir();
            }

            image = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String filePath = uploadDir + File.separator + image;
            filePart.write(filePath);
        }

        // Create Profile object


        // Update profile in database
        ProfileDAO profileDAO = new ProfileDAO();
        profileDAO.updateProfile(new Profile(userId, null, aboutMe, email, phone, image)); // Assuming 'updateProfile' method in ProfileDAO updates the profile with the given data


        // Redirect back to profile page
        resp.sendRedirect("profile");
    }
}
