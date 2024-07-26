package com.bazlur.repository;

import com.bazlur.dto.ProductDTO;
import com.bazlur.eshoppers.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    public List<Product> findAllProducts();
    Optional<Product> findById(Long productId);
}
