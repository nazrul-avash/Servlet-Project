package com.nazrul.eshoppers.web;

import com.nazrul.eshoppers.domain.Product;
import com.nazrul.repository.ProductRepository;
import com.nazrul.service.ProductService;
import com.nazrul.service.ProductServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ProductServiceImplTest {
private static final Product APPLE_IPAD = new Product(1L,"Apple iPad","Apple 35GB ipad", BigDecimal.valueOf(369.99));
private static final Product HEADPHONE    = new Product(2L,"Kinera","an Iem to float", BigDecimal.valueOf(5338.22));
private ProductRepository productRepository;
private ProductService productService;

@Before
public void setUp() throws Exception{
    productRepository = mock(ProductRepository.class);
    productService= new ProductServiceImpl(productRepository);
}
@Test
    public void testFindAllProductsSortedByName(){
    when(productRepository.findAllProducts()).thenReturn(List.of(APPLE_IPAD,HEADPHONE));
    var sortedByName = productService.findAllProductSortedByName();
    Assert.assertEquals(APPLE_IPAD.getName(),sortedByName.get(0).getName());
    Assert.assertEquals(HEADPHONE.getName(),sortedByName.get(1).getName());
}
}
