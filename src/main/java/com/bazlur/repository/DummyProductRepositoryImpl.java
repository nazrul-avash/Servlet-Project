package com.bazlur.repository;

import com.bazlur.dto.ProductDTO;
import com.bazlur.eshoppers.domain.Product;

import java.math.BigDecimal;
import java.util.List;

public class DummyProductRepositoryImpl implements ProductRepository{
    private static final List<Product> All_products = List.of(new Product(1L,"Apple iPad","Apple 35GB ipad", BigDecimal.valueOf(369.99)),
            new Product(2L,"Kinera","an Iem to float", BigDecimal.valueOf(5338.22)),
            new Product(3L,"Framed Poster","music poster", BigDecimal.valueOf(786.22)),
                    new Product(4L,"Samsung","an Galaxy", BigDecimal.valueOf(5938.22)));

    public List<Product> findAllProducts(){
        return All_products;
    }
}
