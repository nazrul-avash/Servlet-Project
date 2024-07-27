package com.nazrul.repository;

import com.nazrul.eshoppers.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    public List<Product> findAllProducts();
    Optional<Product> findById(Long productId);
}