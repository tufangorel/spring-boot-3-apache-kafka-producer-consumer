package com.kafka.consumer.api.mapper;


import com.kafka.consumer.api.dto.CustomerDTO;
import com.kafka.consumer.api.model.Customer;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDTO toCustomerDTO(Customer customer);

    Customer customerDTOToCustomer(CustomerDTO customerDTO);

    Iterable<CustomerDTO> customersToCustomerAllDtos(Iterable<Customer> customers);
}
