package com.bazlur.service;

import com.bazlur.dto.ProductDTO;
import com.bazlur.eshoppers.domain.Product;
import com.bazlur.repository.ProductRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService{
    private ProductRepository productRepository;
    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    @Override
    public List<ProductDTO> findAllProductSortedByName() {
        return productRepository.findAllProducts().stream().map(this::convertToDTO).sorted(Comparator.comparing(ProductDTO::getName)).collect(Collectors.toList());
    }
    private ProductDTO convertToDTO (Product product){
        product.getId();
        product.getDescription();
        product.getPrice();
        product.getName();
    }
}
