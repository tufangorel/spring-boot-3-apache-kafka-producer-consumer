package com.kafka.consumer.api.config.kafka.deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.consumer.api.model.OrderItem;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class OrderItemDeserializer implements Deserializer<OrderItem> {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderItemDeserializer.class.getName());

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public OrderItem deserialize(String topic, byte[] data) {

        try {
            if (data == null){
                LOGGER.error("Null received at deserializing");
                return null;
            }
            LOGGER.info("Deserializing...");
            return objectMapper.readValue(new String(data, "UTF-8"), OrderItem.class);
        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to OrderItem");
        }
    }

    @Override
    public OrderItem deserialize(String topic, Headers headers, byte[] data) {
        return Deserializer.super.deserialize(topic, headers, data);
    }

    @Override
    public void close() {
        Deserializer.super.close();
    }
}