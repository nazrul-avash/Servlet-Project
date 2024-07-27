package com.bazlur.service;

public class CartItemNotFoundException extends RuntimeException {
    public CartItemNotFoundException(String s) {
        super("Cart Item not found");
    }
}
