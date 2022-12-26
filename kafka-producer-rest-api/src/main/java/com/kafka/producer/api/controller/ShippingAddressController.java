package com.kafka.producer.api.controller;


import com.kafka.producer.api.model.Customer;
import com.kafka.producer.api.service.ShippingAddressService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shippingaddress")
public class ShippingAddressController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShippingAddressController.class);

    private ShippingAddressService shippingAddressService;

    @Autowired
    public void setShippingAddressService(ShippingAddressService shippingAddressService) {
        this.shippingAddressService = shippingAddressService;
    }

    @Operation(summary = "Find a customer by shipping address id")
    @GetMapping(value = "/findcustomer/{shippingAddressID}", produces = "application/json")
    public ResponseEntity<?> findCustomerByShippingAddressID(@PathVariable Integer shippingAddressID){
        Customer customer = shippingAddressService.findCustomerByShippingAddressID(shippingAddressID);

        if (customer==null) {
            return ResponseEntity.badRequest()
                    .body("Customer at this shipping address not found!");
        }

        return ResponseEntity.ok(customer);
    }

}