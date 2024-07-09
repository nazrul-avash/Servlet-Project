package com.bazlur.repository;

import com.bazlur.dto.ProductDTO;

import java.math.BigDecimal;
import java.util.List;

public class DummyProductRepositoryImpl implements ProductRepository{
    public List<ProductDTO> findAllProducts(){
        return List.of(new ProductDTO("Apple iPad","Apple 35GB ipad", BigDecimal.valueOf(369.99)),new ProductDTO("Kinera","an Iem to float", BigDecimal.valueOf(5338.22)));
    }
}
