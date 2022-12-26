package com.kafka.producer.api.repository;


import com.kafka.producer.api.model.Customer;
import com.kafka.producer.api.model.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, Integer> {

    @Query("SELECT s.customer FROM ShippingAddress s WHERE s.id = :shippingAddressID")
    Customer findCustomerByShippingAddressID(Integer shippingAddressID);

}