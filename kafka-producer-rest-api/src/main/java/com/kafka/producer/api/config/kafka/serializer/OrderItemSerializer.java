package com.kafka.producer.api.config.kafka.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.producer.api.model.OrderItem;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class OrderItemSerializer implements Serializer<OrderItem> {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderItemSerializer.class.getName());

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public byte[] serialize(String s, OrderItem orderItem) {

        try {
            if (orderItem == null){
                LOGGER.error("Null received at serializing");
                return null;
            }
            LOGGER.info("Serializing orderItem ...");
            return objectMapper.writeValueAsBytes(orderItem);
        } catch (Exception e) {
            throw new SerializationException("Error when serializing OrderItem to byte[]");
        }
    }

    @Override
    public void close() {
        Serializer.super.close();
    }
}