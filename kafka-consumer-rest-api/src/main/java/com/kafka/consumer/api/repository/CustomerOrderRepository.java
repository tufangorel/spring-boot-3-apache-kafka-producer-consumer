package com.kafka.consumer.api.repository;

import com.kafka.consumer.api.model.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Integer> {


}

