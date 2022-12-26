package com.kafka.producer.api.repository;

import com.kafka.producer.api.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer>{

}

