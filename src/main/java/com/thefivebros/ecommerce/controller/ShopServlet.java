package com.thefivebros.ecommerce.controller;


import com.thefivebros.ecommerce.model.Product;
import com.thefivebros.ecommerce.model.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/shop")
public class ShopServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        List<Product> products = ProductDAO.getProducts();
        req.setAttribute("products", products);
        req.getRequestDispatcher("WEB-INF/ecommerce/shop.jsp").forward(req, resp);
    }
}
