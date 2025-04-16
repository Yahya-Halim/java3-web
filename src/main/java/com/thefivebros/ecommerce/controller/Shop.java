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

@WebServlet(value = "/shop")
public class Shop extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Get search query
        String searchQuery = req.getParameter("searchQuery");
        req.setAttribute("searchQuery", searchQuery);

        // Get price range filters
        String minPriceStr = req.getParameter("minPrice");
        String maxPriceStr = req.getParameter("maxPrice");
        Double minPrice = (minPriceStr != null && !minPriceStr.isEmpty()) ? Double.parseDouble(minPriceStr) : null;
        Double maxPrice = (maxPriceStr != null && !maxPriceStr.isEmpty()) ? Double.parseDouble(maxPriceStr) : null;
        req.setAttribute("minPrice", minPrice);
        req.setAttribute("maxPrice", maxPrice);

        // Get limit
        String limitStr = req.getParameter("limit");
        int limit = 10;
        try {
            limit = Integer.parseInt(limitStr);
        } catch (NumberFormatException e) {
            limit = 10;
        }
        req.setAttribute("limit", limit);

        // Get categories
        String[] categoriesArr = req.getParameterValues("categories");
        String categories = "";
        if (categoriesArr != null && categoriesArr.length > 0) {
            categories = String.join(",", categoriesArr);
        }
        req.setAttribute("categories", categories);

        // Get total product count




        // Get current page
        String pageStr = req.getParameter("page");
        int page = 1;
        try {
            page = Integer.parseInt(pageStr);
        } catch (NumberFormatException ignored) {}
        req.setAttribute("page", page);

        int offset = (page - 1) * limit;

        // Get product list
        List<Product> products = ProductDAO.getProducts(limit, offset, categories, minPrice, maxPrice);

        // Sort by price ascending
        products.sort((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()));

        req.setAttribute("products", products);

        // Get all categories
        List<ProductCategory> productCategories = ProductDAO.getAllCategories();
        req.setAttribute("productCategories", productCategories);

        // Forward to shop.jsp
        req.getRequestDispatcher("WEB-INF/ecommerce/shop.jsp").forward(req, resp);
    }
}
