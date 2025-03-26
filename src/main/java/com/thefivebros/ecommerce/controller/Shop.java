package com.thefivebros.ecommerce.controller;


import com.thefivebros.ecommerce.model.Product;
import com.thefivebros.ecommerce.model.ProductCategory;
import com.thefivebros.ecommerce.model.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(value="/shop")
public class Shop extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String limitStr = req.getParameter("limit");
        int limit = 10;
        try {
            limit = Integer.parseInt(limitStr);
        } catch (NumberFormatException e) {
            if (limit < 0) {
                limit = 10;
            }

        }
        int offset = 0;
        String[] categoriesArr = req.getParameterValues("categories");
        String categories = "";
        if (categoriesArr != null && categoriesArr.length > 0) {
            categories = String.join(",", categoriesArr);
        }
        req.setAttribute("categories", categories);
        req.setAttribute("limit", limit);
        List<Product> products = ProductDAO.getProducts(limit, offset, categories);
        req.setAttribute("products", products);
        List<ProductCategory> productCategories = ProductDAO.getAllCategories();
        req.setAttribute("productCategories", productCategories);
        req.getRequestDispatcher("WEB-INF/ecommerce/shop.jsp").forward(req, resp);
    }
}
