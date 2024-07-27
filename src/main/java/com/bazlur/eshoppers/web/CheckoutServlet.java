package com.bazlur.eshoppers.web;

import com.bazlur.eshoppers.CartItemRepositoryImpl;
import com.bazlur.repository.ProductRepositoryImpl;
import com.bazlur.service.CartRepositoryImpl;
import com.bazlur.service.CartService;
import com.bazlur.service.CartServiceImpl;
import com.bazlur.service.SecurityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    public static final Logger logs = LoggerFactory.getLogger(CheckoutServlet.class);
    private CartService cartService = new CartServiceImpl(new CartRepositoryImpl(), new ProductRepositoryImpl(),new CartItemRepositoryImpl());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logs.info("Serving checkout page");
        var currentUser = SecurityContext.getCurrentUser(req);
        var cart = cartService.getCartByUser(currentUser);
        req.setAttribute("cart",cart);
        req.getRequestDispatcher("WEB-INF/checkout.jsp").forward(req,resp);
    }
}
