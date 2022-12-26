package com.kafka.consumer.api.repository;


import com.kafka.consumer.api.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StoreRepository extends JpaRepository<Store, Integer> {
}