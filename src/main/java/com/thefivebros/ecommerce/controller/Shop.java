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
                String sort = req.getParameter("sort");
                req.setAttribute("sort", sort);

                String search = req.getParameter("search");
                req.setAttribute("search", search);

                // Price range
                String minPriceStr = req.getParameter("minPrice");
                String maxPriceStr = req.getParameter("maxPrice");
                Double minPrice = (minPriceStr != null && !minPriceStr.isEmpty()) ? Double.parseDouble(minPriceStr) : null;
                Double maxPrice = (maxPriceStr != null && !maxPriceStr.isEmpty()) ? Double.parseDouble(maxPriceStr) : null;
                req.setAttribute("minPrice", minPriceStr); // Preserve original string for input value
                req.setAttribute("maxPrice", maxPriceStr);

                // Limit
                String limitStr = req.getParameter("limit");
                int limit = 10;
                try {
                        limit = Integer.parseInt(limitStr);
                } catch (NumberFormatException e) {
                        // Keep default
                }
                if (limit <= 0) limit = 10;
                req.setAttribute("limit", limit);

                // Categories
                String[] categoriesArr = req.getParameterValues("categories");
                String categories = "";
                if (categoriesArr != null && categoriesArr.length > 0) {
                        categories = String.join(",", categoriesArr);
                }
                req.setAttribute("categoriesArr", categoriesArr); // for checkbox checks

                // Total count and page
                int totalProducts = ProductDAO.getProductCount(categories);
                int totalPages = totalProducts / limit;
                if (totalProducts % limit != 0) {
                        totalPages++;
                }
                req.setAttribute("totalProducts", totalProducts);
                req.setAttribute("totalPages", totalPages);

                String pageStr = req.getParameter("page");
                int page = 1;
                try {
                        page = Integer.parseInt(pageStr);
                } catch (NumberFormatException ignored) {}
                if (page < 1) page = 1;
                if (page > totalPages) page = totalPages;
                req.setAttribute("page", page);
                int offset = (page - 1) * limit;

                // Page links
                int pageLinks = 5;
                int beginPage = Math.max(1, (page - 1) / pageLinks * pageLinks + 1);
                int endPage = Math.min(totalPages, beginPage + pageLinks - 1);
                req.setAttribute("beginPage", beginPage);
                req.setAttribute("endPage", endPage);

                // First/last product range
                int firstProductShown = offset + 1;
                int lastProductShown = Math.min(offset + limit, totalProducts);
                req.setAttribute("firstProductShown", firstProductShown);
                req.setAttribute("lastProductShown", lastProductShown);

                // Get data
                List<Product> products = ProductDAO.getProducts(limit, offset, categories, minPrice, maxPrice, sort, search);
                req.setAttribute("products", products);

                List<ProductCategory> productCategories = ProductDAO.getAllCategories();
                req.setAttribute("productCategories", productCategories);

                req.getRequestDispatcher("WEB-INF/ecommerce/shop.jsp").forward(req, resp);
        }

}
