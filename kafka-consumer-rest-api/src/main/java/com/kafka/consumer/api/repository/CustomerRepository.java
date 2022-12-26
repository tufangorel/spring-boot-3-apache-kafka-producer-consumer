package com.kafka.consumer.api.repository;

import com.kafka.consumer.api.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer>{

}

