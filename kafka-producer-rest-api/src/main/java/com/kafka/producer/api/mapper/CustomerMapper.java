package com.kafka.producer.api.mapper;


import com.kafka.producer.api.dto.CustomerDTO;
import com.kafka.producer.api.model.Customer;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDTO toCustomerDTO(Customer customer);

    Customer customerDTOToCustomer(CustomerDTO customerDTO);

    Iterable<CustomerDTO> customersToCustomerAllDtos(Iterable<Customer> customers);
}
