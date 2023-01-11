package com.kafka.producer.api.config.kafka;

import com.kafka.producer.api.model.OrderItem;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerConfig.class);

    @Autowired
    private KafkaProducerProperties kafkaProducerProperties;

    @Bean
    public ProducerFactory<String, OrderItem> producerFactory() {

        Map<String, Object> configProps = new HashMap<>();
        configProps.put( ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProducerProperties.getBootstrapServers());
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "com.kafka.producer.api.config.kafka.serializer.OrderItemSerializer");

        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    KafkaTemplate<String, OrderItem> kafkaOrderItemTemplate() {
        KafkaTemplate<String, OrderItem> kafkaTemplate = new KafkaTemplate<>(producerFactory());
        kafkaTemplate.setMessageConverter(new StringJsonMessageConverter());

        kafkaTemplate.setProducerListener(new ProducerListener<String, OrderItem>() {
            @Override
            public void onSuccess(ProducerRecord<String, OrderItem> producerRecord, RecordMetadata recordMetadata) {
                LOGGER.info("ACK from ProducerListener message: {} offset:  {}",
                        producerRecord.value().toString(),
                        recordMetadata.offset());
            }

            @Override
            public void onError(ProducerRecord<String, OrderItem> producerRecord, RecordMetadata recordMetadata, Exception exception) {
                ProducerListener.super.onError(producerRecord, recordMetadata, exception);
                LOGGER.error("Error from ProducerListener message: {} offset:  {}", producerRecord.value(),
                        recordMetadata.offset());
                LOGGER.error("Error from ProducerListener exception : {}",exception.getMessage());
            }
        });
        return kafkaTemplate;
    }

}
