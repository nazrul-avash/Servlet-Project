package com.bazlur.eshoppers.web;

import com.bazlur.dto.UserDTO;
import com.bazlur.eshoppers.domain.UserRepositoryImpl;
import com.bazlur.service.UserService;
import com.bazlur.service.UserServiceImpl;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        if(isValid(userDTO)){
            logs.info("user is valid, creating a new user with: {}",userDTO);
            userService.saveUser(userDTO);
            resp.sendRedirect("/shop/home");
        }
        else{
            logs.info("user sent invalid data: {}",userDTO);
            req.getRequestDispatcher("/WEB-INF/signup.jsp").forward(req,resp);
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
    private boolean isValid(UserDTO userDTO){
        var validationFactory = Validation.buildDefaultValidatorFactory();
        var validator = validationFactory.getValidator();
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
        Map<String, String> errors = new HashMap<>();
        for(ConstraintViolation<UserDTO> violation: violations){
            String path = violation.getPropertyPath().toString();
            if(errors.containsKey(path)){
                String errorMessage = errors.get(path);
                errors.put(path,errorMessage+"<br/>"+violation.getMessage());
            }
            else {
                errors.put(path,violation.getMessage());
            }
        }
        return errors;
    }
}
