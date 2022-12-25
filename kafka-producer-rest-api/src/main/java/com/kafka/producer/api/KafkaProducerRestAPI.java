package com.kafka.producer.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaProducerRestAPI {

	public static void main(String[] args) {
		SpringApplication.run(KafkaProducerRestAPI.class, args);
	}

}