package com.kafka.consumer.api.service;

import com.kafka.consumer.api.model.Customer;
import com.kafka.consumer.api.repository.ShippingAddressRepository;
import org.springframework.stereotype.Service;

@Service
public class ShippingAddressService {

    private final ShippingAddressRepository shippingAddressRepository;

    public ShippingAddressService(ShippingAddressRepository shippingAddressRepository) {
        this.shippingAddressRepository = shippingAddressRepository;
    }

    public Customer findCustomerByShippingAddressID(Integer shippingAddressID) {
        return shippingAddressRepository.findCustomerByShippingAddressID(shippingAddressID);
    }
}
