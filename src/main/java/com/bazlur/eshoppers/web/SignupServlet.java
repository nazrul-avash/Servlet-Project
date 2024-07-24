package com.bazlur.eshoppers.web;

import com.bazlur.dto.UserDTO;
import com.bazlur.eshoppers.domain.UserRepositoryImpl;
import com.bazlur.service.UserService;
import com.bazlur.service.UserServiceImpl;

import com.bazlur.service.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
    private final static Logger logs = LoggerFactory.getLogger(SignupServlet.class);
    private UserService userService = new UserServiceImpl(new UserRepositoryImpl());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logs.info("Serving signup page");
        req.getRequestDispatcher("/WEB-INF/signup.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDTO userDTO = copyParametersTo(req);



        var errors = ValidationUtil.getInstance().validate(userDTO);
        logs.info(errors.getClass().toString());
        logs.info(errors.toString());
        if(!errors.isEmpty()){
            logs.info("user sent invalid data: {}",userDTO);
            req.setAttribute("userDto",userDTO);
            req.setAttribute("errors",errors);
            req.getRequestDispatcher("/WEB-INF/signup.jsp").forward(req,resp);

        }
        else if(userService.isNotUniqueUsername(userDTO)){
           logs.info("username: {} is already exist",userDTO.getUsername());
           errors.put("username","The username already exists");
           req.setAttribute("errors",errors);
            req.setAttribute("userDto",userDTO);
           req.getRequestDispatcher("/WEB-INF/signup.jsp").forward(req,resp);
        }
        else {
            logs.info("user is valid, creating a new user with: {}",userDTO);
            userService.saveUser(userDTO);
            resp.sendRedirect("/login");
        }
    }
    private UserDTO copyParametersTo(HttpServletRequest req){
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(req.getParameter("firstName"));
        userDTO.setLastName(req.getParameter("lastName"));
        userDTO.setPassword(req.getParameter("password"));
        userDTO.setPasswordConfirmed(req.getParameter("passwordConfirmed"));
        userDTO.setEmail(req.getParameter("email"));
        userDTO.setUsername(req.getParameter("username"));
        return userDTO;
    }

}
