package com.kafka.producer.api.repository;

import com.kafka.producer.api.model.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Integer> {


}

