package com.bazlur.eshoppers.web;

import java.util.List;

public interface ProductService {
    List<ProductDTO> findAllProductSortedByName();
}
