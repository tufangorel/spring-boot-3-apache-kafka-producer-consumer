package com.kafka.consumer.api.service;


import com.kafka.consumer.api.model.OrderItem;
import com.kafka.consumer.api.repository.OrderItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Transactional
    public OrderItem save(OrderItem orderItem){
        return orderItemRepository.save(orderItem);
    }

    public Optional<OrderItem> findByID(Integer id){
        return orderItemRepository.findById(id);
    }

    public List<OrderItem> findAll() {
        return orderItemRepository.findAll();
    }

    @Transactional
    public void deleteOrderItemByID(Integer id) {
        orderItemRepository.deleteById(id);
    }
}