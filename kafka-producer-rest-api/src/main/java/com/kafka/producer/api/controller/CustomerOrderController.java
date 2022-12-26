package com.kafka.producer.api.controller;

import com.kafka.producer.api.model.CustomerOrder;
import com.kafka.producer.api.service.CustomerOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;


@RestController
@RequestMapping("/customerorder")
public class CustomerOrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerOrderController.class);

    private CustomerOrderService customerOrderService;

    @Autowired
    public void setCustomerService(CustomerOrderService customerOrderService) {
        this.customerOrderService = customerOrderService;
    }

    @Operation(summary = "Create a new customer order record")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "201", description = "customer order created", content = { @Content(mediaType = "application/json")} ),
            @ApiResponse(responseCode = "404", description = "Bad request") })
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody CustomerOrder customerOrder) {
        CustomerOrder response = customerOrderService.save(customerOrder);
        return new ResponseEntity<CustomerOrder>( response, HttpStatus.OK );
    }

    @Operation(summary = "View a list of customer orders")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
            @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource"),
            @ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found")
    })
    @GetMapping(value = "/list", produces = "application/json")
    public ResponseEntity<Iterable<CustomerOrder>> list(){
        Iterable<CustomerOrder> customerOrders = customerOrderService.findAll();
        return new ResponseEntity<Iterable<CustomerOrder>>( customerOrders, HttpStatus.OK );
    }

    @Operation(summary = "Delete a customer order")
    @DeleteMapping(value="/delete/{id}", produces = "application/json")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        customerOrderService.deleteCustomerOrder(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Update a customer order")
    @PutMapping(value = "/update/{id}", produces = "application/json")
    public ResponseEntity<?> updateCustomer(@PathVariable Integer id, @RequestBody CustomerOrder customer){
        Optional<CustomerOrder> storedCustomerOrder = customerOrderService.findByID(id);
        storedCustomerOrder.get().setOrderDate(LocalDateTime.now());
        customerOrderService.save(storedCustomerOrder.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}