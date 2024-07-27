package com.nazrul.eshoppers.web;

import com.nazrul.dto.LoginDTO;
import com.nazrul.eshoppers.domain.User;
import com.nazrul.eshoppers.domain.UserRepositoryImpl;
import com.nazrul.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final Logger logs = LoggerFactory.getLogger(LoginServlet.class);
    UserService userService = new UserServiceImpl(new UserRepositoryImpl());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logs.info("Serving Login Page");
        String logout = req.getParameter("logout");
        if (logout !=null && Boolean.parseBoolean(logout)){
            req.setAttribute("message","You have been successfully logged out");
        }
        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginDTO loginDTO = new LoginDTO(req.getParameter("username"),req.getParameter("password"));
        logs.info("Received login data: {}",loginDTO);
        var errors = ValidationUtil.getInstance().validate(loginDTO);
        if(!errors.isEmpty()){
            logs.info("Failed to login");
            req.setAttribute("errors",errors);
            req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req,resp);
        }
        try{
            login(loginDTO,req);
            logs.info("Login Successfull");
            resp.sendRedirect("/shop/home");
        }catch(UserNotFoundException e){
            logs.error("Incorrect username/password",e);
            errors.put("username","Incorrect username/password");
            req.setAttribute("errors",errors);
            req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req,resp);
        }

    }
    private void login(LoginDTO loginDTO, HttpServletRequest req) throws UserNotFoundException{
        User user = userService.verifyUser(loginDTO);
        SecurityContext.login(req,user);

    }
}
