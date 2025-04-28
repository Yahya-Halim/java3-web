package com.thefivebros.ecommerce.controller;

import com.thefivebros.ecommerce.model.OrderDAO;
import com.thefivebros.ecommerce.model.ShoppingCart;
import com.thefivebros.shared.authorize_net.ChargeCreditCard;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.authorize.api.contract.v1.ANetApiResponse;
import net.authorize.api.contract.v1.CreateTransactionResponse;
import net.authorize.api.contract.v1.MessageTypeEnum;
import net.authorize.api.contract.v1.TransactionResponse;

import java.io.IOException;

@WebServlet("/checkout")
public class Checkout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ShoppingCart cart = (ShoppingCart)req.getAttribute("cart");
        double salesTaxRate = 0.07;
        double shippingCost = 0;
        double discountPercent = 0;
        req.setAttribute("pageTitle", "Checkout");
        req.getRequestDispatcher("WEB-INF/ecommerce/checkout.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] billingInfo = new String[8];
        billingInfo[0] = req.getParameter("firstName");
        billingInfo[1] = req.getParameter("lastName");
        billingInfo[2]  = req.getParameter("address");
        billingInfo[3]  = req.getParameter("city");
        billingInfo[4]  = req.getParameter("state");
        billingInfo[5]  = req.getParameter("zip");
        billingInfo[6]  = "USA"; // Used for country input
        billingInfo[7]  = ""; // Used for phone number

        String email = req.getParameter("email");

        String[] sameAddressCheck = req.getParameterValues("same_address");
        boolean sameAddress = false;
//        if(sameAddressCheck != null) {
//            sameAddress = true;
//        }
        // Todo: If the checkbox is unchecked, get the shipping info
        sameAddress = true;

        String[] shippingInfo = new String[7];
        if(sameAddress) {
            shippingInfo[0] = billingInfo[0];
            shippingInfo[1] = billingInfo[1];
            shippingInfo[2] = billingInfo[2];
            shippingInfo[3] = billingInfo[3];
            shippingInfo[4] = billingInfo[4];
            shippingInfo[5] = billingInfo[5];
            shippingInfo[6] = billingInfo[6];
        }

        String[] ccInfo = new String[4];
        ccInfo[0] = req.getParameter("cc-number");
        ccInfo[1] = req.getParameter("cc-expiration");
        ccInfo[2] = req.getParameter("cc-cvv");

        // Don't forget to validate and check for errors
        boolean errorFound = false;

        if(!errorFound) {
            HttpSession session = req.getSession();
            ShoppingCart cart = (ShoppingCart)session.getAttribute("cart");
            if(cart != null) {
                int newOrderId = OrderDAO.addOrder(shippingInfo, email, cart.toString());
                if(newOrderId > 0) {
                    // The order was added
                    Double amount = cart.getTotalPrice();
                    String response = ChargeCreditCard.run(amount, ccInfo, billingInfo, shippingInfo, email, sameAddress);
                    // Parse the response to determine results
                    if (response.contains("Success")) {
                        session.removeAttribute("cart");
                        session.setAttribute("newOrderId", newOrderId);
                        //Send a confirmation email
                        session.setAttribute("flashMessageSuccess", response);
                        resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + "/order-confirmation"));
                        return;
                    } else {
                        session.setAttribute("flashMessageDanger", response);
                    }
                } else {
                    // Order failed in database
                    session.setAttribute("flashMessageDanger", "Your order could not be processed.");
                }
            } else {
                // Cart doesn't exist
            }
        } else {
            // Validation errors
        }
        req.setAttribute("pageTitle", "Checkout");
        req.getRequestDispatcher("WEB-INF/ecommerce/checkout.jsp").forward(req, resp);
    }
}