package com.bazlur.eshoppers.web;

import com.bazlur.dto.ShippingAddressDTO;
import com.bazlur.eshoppers.CartItemRepositoryImpl;
import com.bazlur.eshoppers.domain.ShippingAddress;
import com.bazlur.repository.OrderRepositoryImpl;
import com.bazlur.repository.ProductRepositoryImpl;
import com.bazlur.repository.ShippingAddressRepositoryImpl;
import com.bazlur.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@WebServlet("/order")
public class OrderServlet extends HttpServlet {
    public static final Logger logs = LoggerFactory.getLogger(CheckoutServlet.class);
    private CartService cartService = new CartServiceImpl(new CartRepositoryImpl(), new ProductRepositoryImpl(),new CartItemRepositoryImpl());
    private OrderService orderService = new OrderServiceImpl(new OrderRepositoryImpl(), new ShippingAddressRepositoryImpl(), new CartRepositoryImpl());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        addCartToUi(req);
        req.setAttribute("countries",getCountries());
        req.getRequestDispatcher("/WEB-INF/order.jsp").forward(req,resp);
    }
    private void addCartToUi(HttpServletRequest req){
        if(SecurityContext.isAuthenticated(req)){
            var currentUser = SecurityContext.getCurrentUser(req);
            var cart = cartService.getCartByUser(currentUser);
            req.setAttribute("cart",cart);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logs.info("Handle order request form");
        var shippingAddress = copyParametersTo(req);
        logs.info("shippingAddress information: {}", shippingAddress);
        var errors = ValidationUtil.getInstance().validate(shippingAddress);
        if(!errors.isEmpty()){
            req.setAttribute("countries",getCountries());
            req.setAttribute("errors",errors);
            req.setAttribute("shippingAddress",shippingAddress);
            addCartToUi(req);
            req.getRequestDispatcher("/WEB-INF/order.jsp").forward(req,resp);
        }
        else {
            orderService.processOrder(shippingAddress,SecurityContext.getCurrentUser(req));
            resp.sendRedirect("/home?orderSuccess=true");
        }
    }
    private ShippingAddressDTO copyParametersTo(HttpServletRequest req){
        var shippingAddress = new ShippingAddressDTO();
        shippingAddress.setAddress(req.getParameter("address"));
        shippingAddress.setAddress2(req.getParameter("address2"));
        shippingAddress.setState(req.getParameter("state"));
        shippingAddress.setZip(req.getParameter("zip"));
        shippingAddress.setCountry(req.getParameter("country"));
        shippingAddress.setMobileNumber(req.getParameter("mobileNumber"));
        return shippingAddress;
    }
    private List<String> getCountries(){
        return List.of("Bangladesh", "Canada");
    }
}
