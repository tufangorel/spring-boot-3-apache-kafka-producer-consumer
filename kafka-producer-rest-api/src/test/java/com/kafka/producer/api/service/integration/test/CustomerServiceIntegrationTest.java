package com.kafka.producer.api.service.integration.test;


import com.kafka.producer.api.KafkaProducerRestAPI;
import com.kafka.producer.api.model.*;
import com.kafka.producer.api.service.CustomerService;
import com.kafka.producer.api.service.ShippingAddressService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(classes = KafkaProducerRestAPI.class)
@ActiveProfiles("test")
public class CustomerServiceIntegrationTest {

    private final CustomerService customerService;
    private final ShippingAddressService shippingAddressService;
    private final EntityManager entityManager;

    @Autowired
    public CustomerServiceIntegrationTest(CustomerService customerService,
        ShippingAddressService shippingAddressService, EntityManager entityManager) {

        this.customerService = customerService;
        this.shippingAddressService = shippingAddressService;
        this.entityManager = entityManager;
        assertThat(this.entityManager != null);
    }

    @Test
    public void saveCustomerTest() {

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

        Customer savedRecord = customerService.save(customer);
        assertThat( savedRecord.getShippingAddress() != null);

        Customer customerFromShippingAddressID = shippingAddressService.findCustomerByShippingAddressID(shippingAddress.getId());
        assertThat( customerFromShippingAddressID != null);
        assertThat( savedRecord == customerFromShippingAddressID );
    }

    @Test
    public void findAllTest() {
        List<Customer> customerList =customerService.findAllByJPARepository();
        assertThat( customerList.size() > 0);
    }

    @Test
    public void saveCustomerWithOrderTest() {

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

        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setCustomer(customer);
        customerOrder.setOrderDate(LocalDateTime.now());

        OrderItem orderItem1 = new OrderItem();
        orderItem1.setQuantity(1);
        OrderItem orderItem2 = new OrderItem();
        orderItem2.setQuantity(2);

        Customer savedRecord = customerService.save(customer);
        assertThat( savedRecord.getShippingAddress() != null);

        Customer customerFromShippingAddressID = shippingAddressService.findCustomerByShippingAddressID(shippingAddress.getId());
        assertThat( customerFromShippingAddressID != null);
        assertThat( savedRecord == customerFromShippingAddressID );
    }

    @Test
    public void findAllByQueryDSLTest() {

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

        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setCustomer(customer);
        customerOrder.setOrderDate(LocalDateTime.now());

        OrderItem orderItem1 = new OrderItem();
        orderItem1.setQuantity(1);
        OrderItem orderItem2 = new OrderItem();
        orderItem2.setQuantity(2);

        Customer savedRecord = customerService.save(customer);

        List<Customer> customerList =customerService.findAllByJPARepository();
        assertThat( customerList.size() > 0);
    }

}
