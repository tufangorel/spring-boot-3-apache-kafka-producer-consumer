package com.kafka.consumer.api.repository;


import com.kafka.consumer.api.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, String> {

}

