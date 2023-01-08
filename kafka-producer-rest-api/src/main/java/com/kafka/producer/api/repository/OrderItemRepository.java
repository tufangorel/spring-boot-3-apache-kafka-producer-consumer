package com.kafka.producer.api.repository;


import com.kafka.producer.api.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, String> {

}

