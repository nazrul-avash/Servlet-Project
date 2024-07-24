package com.bazlur.repository;

import com.bazlur.dto.ProductDTO;
import com.bazlur.eshoppers.domain.Product;

import java.util.List;

public interface ProductRepository {
    public List<Product> findAllProducts();
}
