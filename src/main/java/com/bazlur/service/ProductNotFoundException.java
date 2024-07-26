package com.bazlur.service;

import com.bazlur.eshoppers.domain.Product;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
