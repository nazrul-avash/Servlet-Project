package com.nazrul.service;

import com.nazrul.eshoppers.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SecurityContext {
    public static final String AUTHENTICATION_KEY = "auth.Key";
    public static void login(HttpServletRequest req, User user){
        HttpSession oldSession = req.getSession(false);
        if(oldSession != null ){
            oldSession.invalidate();
        }
        HttpSession session = req.getSession(true);
        session.setAttribute(AUTHENTICATION_KEY,user);
    }
    public static void logOut(HttpServletRequest request){
        HttpSession session = request.getSession(true);
        session.removeAttribute(AUTHENTICATION_KEY);
    }
    public static User getCurrentUser(HttpServletRequest request){
        HttpSession session = request.getSession(true);
        return (User) session.getAttribute(AUTHENTICATION_KEY);
    }
    public static boolean isAuthenticated(HttpServletRequest request){
        HttpSession session = request.getSession(true);
        return session.getAttribute(AUTHENTICATION_KEY) != null;
    }
}