package com.nazrul.eshoppers.web;

import com.nazrul.dto.ProductDTO;
import com.nazrul.eshoppers.CartItemRepositoryImpl;
import com.nazrul.repository.ProductRepositoryImpl;
import com.nazrul.service.*;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    private ProductService productService
            = new ProductServiceImpl(new ProductRepositoryImpl());

    private CartService cartService
            = new CartServiceImpl(new CartRepositoryImpl(), new ProductRepositoryImpl(),
            new CartItemRepositoryImpl());
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeServlet.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("Logging on");
        final String attribute = req.getParameter("orderSuccess");
        if(attribute != null && Boolean.parseBoolean(attribute)){
            req.setAttribute("message","Congratulation!, Order Successful");
        }

        List<ProductDTO> allProducts= productService.findAllProductSortedByName();
        if (SecurityContext.isAuthenticated(req)) {
            var currentUser = SecurityContext.getCurrentUser(req);
            var cart = cartService.getCartByUser(currentUser);
            req.setAttribute("cart", cart);
        }
        req.setAttribute("products",allProducts);
        req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req,resp);
    }
}
