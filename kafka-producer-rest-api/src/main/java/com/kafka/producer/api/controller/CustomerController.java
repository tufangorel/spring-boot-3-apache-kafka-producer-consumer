package com.kafka.producer.api.controller;

import com.kafka.producer.api.dto.CustomerDTO;
import com.kafka.producer.api.mapper.CustomerMapper;
import com.kafka.producer.api.model.Customer;
import com.kafka.producer.api.service.CustomerService;
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

import java.util.Optional;


@RestController
@RequestMapping("/customer")
public class CustomerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    private CustomerMapper customerMapper;
    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService, CustomerMapper customerMapper) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    @Operation(summary = "Create a new customer record")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "201", description = "customer created", content = { @Content(mediaType = "application/json")} ),
            @ApiResponse(responseCode = "404", description = "Bad request") })
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody CustomerDTO customerDTO) {
        Customer customer = customerService.save( customerMapper.customerDTOToCustomer( customerDTO ) );
        CustomerDTO customerDTOSaved = customerMapper.toCustomerDTO(customer);
        return new ResponseEntity<CustomerDTO>( customerDTOSaved, HttpStatus.OK );
    }

    @Operation(summary = "Create a new customer record")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "201", description = "customer created", content = { @Content(mediaType = "application/json")} ),
            @ApiResponse(responseCode = "404", description = "Bad request") })
    @PostMapping("/saveprogrammatic")
    public ResponseEntity<?> saveprogrammatic(@RequestBody CustomerDTO customerDTO) {
        Customer customer = customerService.saveWithProgrammaticTransactionManagement( customerMapper.customerDTOToCustomer( customerDTO ) );
        CustomerDTO customerDTOSaved = customerMapper.toCustomerDTO(customer);
        return new ResponseEntity<CustomerDTO>( customerDTOSaved, HttpStatus.OK );
    }

    @Operation(summary = "View a list of customers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
            @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource"),
            @ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found")
    })
    @GetMapping(value = "/list", produces = "application/json")
    public Iterable<CustomerDTO> list(){
        Iterable<Customer> customers = customerService.findAllByJPARepository();
        Iterable<CustomerDTO> customerAllDtos = customerMapper.customersToCustomerAllDtos(customers);
        return customerAllDtos;
    }

    @Operation(summary = "Delete a customer")
    @DeleteMapping(value="/delete/{id}", produces = "application/json")
    public ResponseEntity delete(@PathVariable Integer id){
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Update a customer")
    @PutMapping(value = "/update/{id}", produces = "application/json")
    public ResponseEntity updateCustomer(@PathVariable Integer id, @RequestBody Customer customer){
        Optional<Customer> storedCustomer = customerService.findCustomerByID(id);
        storedCustomer.get().setAge(customer.getAge());
        customerService.save(storedCustomer.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
