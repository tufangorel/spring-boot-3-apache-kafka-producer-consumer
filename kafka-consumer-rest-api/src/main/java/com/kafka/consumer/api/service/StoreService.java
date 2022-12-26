package com.kafka.consumer.api.service;


import com.kafka.consumer.api.model.Store;
import com.kafka.consumer.api.repository.StoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class StoreService {

    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Transactional
    public Store saveStoreWithProducts(Store store){
        return storeRepository.save(store);
    }

    @Transactional
    public List<Store> saveStoresWithProducts( List<Store> stores){
        return storeRepository.saveAll(stores);
    }

    public List<Store> findAll(){
        return storeRepository.findAll();
    }

}