package com.kafka.consumer.api.service;


import com.kafka.consumer.api.model.OrderItem;
import com.kafka.consumer.api.repository.OrderItemRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public OrderItemService(OrderItemRepository orderItemRepository,
                            KafkaTemplate<String, Object> kafkaTemplate) {
        this.orderItemRepository = orderItemRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Transactional
    public OrderItem save(OrderItem orderItem){

        kafkaTemplate.send("orders", orderItem);
        return orderItemRepository.save(orderItem);
    }

    public Optional<OrderItem> findByID(String id){
        return orderItemRepository.findById(id);
    }

    public List<OrderItem> findAll() {
        return orderItemRepository.findAll();
    }

    @Transactional
    public void deleteOrderItemByID(String id) {
        orderItemRepository.deleteById(id);
    }
}