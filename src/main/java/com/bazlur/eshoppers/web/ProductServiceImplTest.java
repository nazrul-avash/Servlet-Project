package com.bazlur.eshoppers.web;

import com.bazlur.dto.ProductDTO;
import com.bazlur.repository.ProductRepository;
import com.bazlur.service.ProductService;
import com.bazlur.service.ProductServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ProductServiceImplTest {
private static final ProductDTO APPLE_IPAD = new ProductDTO("Apple iPad","Apple 35GB ipad", BigDecimal.valueOf(369.99));
private static final ProductDTO HEADPHONE    = new ProductDTO("Kinera","an Iem to float", BigDecimal.valueOf(5338.22));
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
