package com.kafka.producer.api.repository;

import com.kafka.producer.api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}