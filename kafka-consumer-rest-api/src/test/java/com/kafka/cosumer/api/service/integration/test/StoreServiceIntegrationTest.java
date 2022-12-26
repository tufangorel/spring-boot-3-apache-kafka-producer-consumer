package com.kafka.cosumer.api.service.integration.test;


import com.kafka.consumer.api.KafkaConsumerRestAPI;
import com.kafka.consumer.api.model.Product;
import com.kafka.consumer.api.model.Store;
import com.kafka.consumer.api.service.StoreService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = KafkaConsumerRestAPI.class)
@ActiveProfiles("test")
public class StoreServiceIntegrationTest {

    private final StoreService storeService;

    @Autowired
    public StoreServiceIntegrationTest(StoreService storeService) {
        this.storeService = storeService;
    }

    @Test
    public void saveCustomerWithOrderTest() {

        Store store1 = new Store();
        Store store2 = new Store();
        Product product1 = new Product();
        product1.setName("product1");
        Product product2 = new Product();
        product2.setName("product2");
        Product product3 = new Product();
        product3.setName("product3");

        store1.addProduct(product1);
        store1.addProduct(product2);
        store1.addProduct(product3);
        store2.addProduct(product1);


        List<Store> savedRecordList = storeService.saveStoresWithProducts(List.of(store1,store2));
        assertThat( savedRecordList != null);
        assertThat( savedRecordList.size() > 0 );
        assertThat( savedRecordList.size() == 2 );
        assertThat( savedRecordList.get(0).getProducts() != null);
        assertThat( savedRecordList.get(1).getProducts() != null);

    }
}
