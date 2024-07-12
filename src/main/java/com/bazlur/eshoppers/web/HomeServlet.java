package com.bazlur.eshoppers.web;

import com.bazlur.dto.ProductDTO;
import com.bazlur.repository.DummyProductRepositoryImpl;
import com.bazlur.service.ProductService;
import com.bazlur.service.ProductServiceImpl;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeServlet.class);

    private ProductService productService = new ProductServiceImpl(new DummyProductRepositoryImpl());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("Logging on");
        for(int i  = 0; i<1000;i++){
           LOGGER.info("Dummy Log");
        }
        List<ProductDTO> allProducts= productService.findAllProductSortedByName();
        req.setAttribute("products",allProducts);
        req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req,resp);
    }
}
