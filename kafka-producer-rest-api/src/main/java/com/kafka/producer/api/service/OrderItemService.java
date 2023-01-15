package com.kafka.producer.api.service;


import com.kafka.producer.api.config.kafka.KafkaProducerProperties;
import com.kafka.producer.api.model.OrderItem;
import com.kafka.producer.api.repository.OrderItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class OrderItemService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderItemService.class.getName());

    private KafkaProducerProperties kafkaProducerProperties;
    private final OrderItemRepository orderItemRepository;
    private final KafkaTemplate<String, Object> kafkaProducerTemplate;

    public OrderItemService(OrderItemRepository orderItemRepository,
                            KafkaTemplate<String, Object> kafkaProducerTemplate, KafkaProducerProperties kafkaProducerProperties) {
        this.orderItemRepository = orderItemRepository;
        this.kafkaProducerTemplate = kafkaProducerTemplate;
        this.kafkaProducerProperties = kafkaProducerProperties;
    }

    @Transactional
    public OrderItem save(OrderItem orderItem){

        OrderItem resultOrderItem = null;
        try {
            UUID UUIDVal = UUID.randomUUID();
            orderItem.setId(UUIDVal.toString());
            String topic = kafkaProducerProperties.getTopicName();

            CompletableFuture<SendResult<String, Object>> future = kafkaProducerTemplate.send(topic, orderItem.getId(), orderItem);
            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    LOGGER.info("The record with key : {}, value : {} is produced successfully to offset {}",
                            result.getProducerRecord().key(), result.getProducerRecord().value(),
                            result.getRecordMetadata().offset());
                }
                else {
                    LOGGER.error("The record with key: {}, value: {} cannot be processed! caused by {}",
                            result.getProducerRecord().key(), result.getProducerRecord().value(),
                            ex.getMessage());
                }
            });

            resultOrderItem = orderItemRepository.save(orderItem);
        }catch(Exception ex) {
            LOGGER.error("Kafka producer send message exception details : {}", ex.getCause().getMessage());
        }

        return resultOrderItem;
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