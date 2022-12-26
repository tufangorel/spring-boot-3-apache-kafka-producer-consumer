package com.kafka.cosumer.api.service.integration.test;


import com.kafka.consumer.api.KafkaConsumerRestAPI;
import com.kafka.consumer.api.model.*;
import com.kafka.consumer.api.service.CustomerOrderService;
import com.kafka.consumer.api.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(classes = KafkaConsumerRestAPI.class)
@ActiveProfiles("test")
public class CustomerOrderServiceIntegrationTest {

    private final CustomerService customerService;
    private final CustomerOrderService customerOrderService;

    @Autowired
    public CustomerOrderServiceIntegrationTest(CustomerService customerService, CustomerOrderService customerOrderService) {
        this.customerService = customerService;
        this.customerOrderService = customerOrderService;
    }

    @Test
    public void saveCustomerWithOrdersTest() {

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

        Customer savedCustomerRecord = customerService.save(customer);
        System.out.print(savedCustomerRecord);
        assertThat( savedCustomerRecord.getShippingAddress() != null);

        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setCustomer(customer);
        customerOrder.setOrderDate(LocalDateTime.now());

        OrderItem orderItem1 = new OrderItem();
        orderItem1.setQuantity(1);
        orderItem1.setCustomerOrder(customerOrder);
        OrderItem orderItem2 = new OrderItem();
        orderItem2.setQuantity(2);
        orderItem2.setCustomerOrder(customerOrder);

        customerOrder.addOrderItem(orderItem1);
        customerOrder.addOrderItem(orderItem2);

        CustomerOrder savedCustomerOrder = customerOrderService.save(customerOrder);

        assertThat( savedCustomerOrder != null);
    }
}
