package com.bazlur.service;

import com.bazlur.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> findAllProductSortedByName();
}
