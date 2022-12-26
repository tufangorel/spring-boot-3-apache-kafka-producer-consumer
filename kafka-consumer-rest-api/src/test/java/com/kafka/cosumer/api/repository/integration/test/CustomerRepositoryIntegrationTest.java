package com.kafka.cosumer.api.repository.integration.test;

import com.kafka.consumer.api.KafkaConsumerRestAPI;
import com.kafka.consumer.api.model.Address;
import com.kafka.consumer.api.model.Customer;
import com.kafka.consumer.api.model.ShippingAddress;
import com.kafka.consumer.api.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = KafkaConsumerRestAPI.class)
@ActiveProfiles("test")
public class CustomerRepositoryIntegrationTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void shouldSaveUser() {
        Customer customer = new Customer();
        customer.setName("name1");
        customer.setAge(1);

        ShippingAddress shippingAddress = new ShippingAddress();
        Address address = new Address();
        address.setCountry("TR");
        address.setCity("Ankara");
        address.setStreetName("KaleSokak");
        shippingAddress.setAddress(address);
        customer.setShippingAddress(shippingAddress);

        Customer savedCustomer = customerRepository.save(customer);
        assertThat(savedCustomer).isEqualTo(customer);
    }

}