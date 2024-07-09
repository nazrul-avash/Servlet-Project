package com.bazlur.repository;

import com.bazlur.dto.ProductDTO;

import java.util.List;

public interface ProductRepository {
    public List<ProductDTO> findAllProducts();
}
