package com.kafka.consumer.api.service;


import com.kafka.consumer.api.model.Product;
import com.kafka.consumer.api.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

}