package com.bazlur.eshoppers.web;

import com.bazlur.eshoppers.CartItemRepositoryImpl;
import com.bazlur.eshoppers.domain.Cart;
import com.bazlur.eshoppers.domain.User;
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

@WebServlet("/add-to-cart")
public class CartServlet extends HttpServlet {
    private CartService cartService = new CartServiceImpl(new CartRepositoryImpl(),new ProductRepositoryImpl(),new CartItemRepositoryImpl());
   private static final Logger logs = LoggerFactory.getLogger(CartServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var productId = req.getParameter("productId");
        logs.info("received request to add product with id: {} to cart",productId);
        var cart = getCart(req);
        cartService.addProductToCart(productId, cart);
        resp.sendRedirect("/home");
    }

    private Cart getCart(HttpServletRequest req){
        final User currentUser = SecurityContext.getCurrentUser(req);
        return cartService.getCartByUser(currentUser);
    }
}
