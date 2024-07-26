package com.bazlur.eshoppers.web;

import com.bazlur.dto.ProductDTO;
import com.bazlur.eshoppers.CartItemRepositoryImpl;
import com.bazlur.repository.ProductRepositoryImpl;
import com.bazlur.service.*;
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
