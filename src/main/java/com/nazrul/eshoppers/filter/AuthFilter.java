package com.nazrul.eshoppers.filter;

import com.nazrul.service.SecurityContext;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Stream;
@WebFilter(urlPatterns = "/*")
public class AuthFilter implements Filter {
    private static final String[] allowed_contents ={".css",".js",".jpg","home","login","signup"};
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
        var httpServletRequest = (HttpServletRequest)request;
        var requestedUri = httpServletRequest.getRequestURI();
        boolean allowed = Stream.of(allowed_contents).anyMatch(requestedUri::contains);
        if(requestedUri.equals("/")|| allowed|| SecurityContext.isAuthenticated(httpServletRequest)){
            chain.doFilter(request,response);
        }
        else {
            ((HttpServletResponse)response).sendRedirect("/shop/login");
        }
    }
}