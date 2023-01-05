package com.kafka.consumer.api.service.kafka.topic.listener;

import com.kafka.consumer.api.model.OrderItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.kafka.annotation.KafkaListener;

@Component
public class KafkaOrderItemTopicListenerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaOrderItemTopicListenerService.class.getName());

    @KafkaListener(topics = "orders", groupId = "group_id", containerFactory = "orderItemKafkaListener")
    public void consumeOrderItem(OrderItem orderItem) {
        LOGGER.info("OrderItem received from Kafka broker = " + orderItem);
    }

}