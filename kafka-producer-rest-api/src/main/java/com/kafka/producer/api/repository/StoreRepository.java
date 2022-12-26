package com.kafka.producer.api.repository;


import com.kafka.producer.api.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StoreRepository extends JpaRepository<Store, Integer> {
}