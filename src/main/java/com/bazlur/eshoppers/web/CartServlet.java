package com.bazlur.eshoppers.web;

import com.bazlur.eshoppers.domain.Cart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add-to-cart")
public class CartServlet extends HttpServlet {
   private static final Logger logs = LoggerFactory.getLogger(CartServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var productId = req.getParameter("productId");
        logs.info("received request to add product with id: {} to cart",productId);
        var cart = getCart(req);
        addProductToCart(productId,cart);
        resp.sendRedirect("/home");
    }
    private void addProductToCart(String productId, Cart cart){

    }
    private Cart getCart(HttpServletRequest req){
        return new Cart();
    }
}
