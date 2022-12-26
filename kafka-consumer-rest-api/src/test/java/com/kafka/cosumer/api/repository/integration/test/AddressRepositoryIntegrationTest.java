package com.kafka.cosumer.api.repository.integration.test;


import com.kafka.consumer.api.KafkaConsumerRestAPI;
import com.kafka.consumer.api.model.Address;
import com.kafka.consumer.api.repository.AddressRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = KafkaConsumerRestAPI.class)
public class AddressRepositoryIntegrationTest {

    @Autowired
    private AddressRepository addressRepository;

    @Test
    public void shouldSaveAddress() {

        Address address = new Address();
        address.setCountry("TR");
        address.setCity("Ankara");
        address.setStreetName("KaleSokak");

        Address savedAddress = addressRepository.save(address);
        assertThat(savedAddress).isEqualTo(address);
    }

}
