package com.kafka.cosumer.api.service.unit.test;


import com.kafka.consumer.api.model.Product;
import com.kafka.consumer.api.repository.ProductRepository;
import com.kafka.consumer.api.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceUnitTest {

    @InjectMocks
    private ProductService productService;
    @Mock
    private ProductRepository productRepository;


    @Test
    public void injectMocksTest() {
        Assertions.assertNotNull(productService);
        Assertions.assertNotNull(productRepository);
    }

    @Test
    public void findAllTest() {

        List<Product> products = new ArrayList<>();
        Product p1 = new Product();
        p1.setId(1);
        Product p2 = new Product();
        p2.setId(2);
        products.add(p1);
        products.add(p2);
        when(productRepository.findAll()).thenReturn(products);

        List<Product> response = productService.findAll();
        Assertions.assertNotNull(response);
        Assertions.assertEquals( response.size(), 2 );
    }


}
