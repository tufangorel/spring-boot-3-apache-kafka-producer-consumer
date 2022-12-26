package com.kafka.producer.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication( exclude = {DataSourceAutoConfiguration.class} )
public class KafkaProducerRestAPI {

	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerRestAPI.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(KafkaProducerRestAPI.class, args);
		LOGGER.info("Spring Boot application KafkaProducerRestAPI started!");
	}

}