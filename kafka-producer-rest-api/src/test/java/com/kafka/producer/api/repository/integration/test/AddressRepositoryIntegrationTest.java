package com.kafka.producer.api.repository.integration.test;


import com.kafka.producer.api.model.Address;
import com.kafka.producer.api.repository.AddressRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
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
