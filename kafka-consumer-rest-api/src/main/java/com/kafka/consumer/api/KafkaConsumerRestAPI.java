package com.kafka.consumer.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class KafkaConsumerRestAPI
{

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerRestAPI.class.getName());

    public static void main(String[] args) {
        SpringApplication.run(KafkaConsumerRestAPI.class, args);
    }

}
