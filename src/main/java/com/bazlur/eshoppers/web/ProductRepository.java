package com.bazlur.eshoppers.web;

import java.util.List;

public interface ProductRepository {
    public List<ProductDTO> findAllProducts();
}
