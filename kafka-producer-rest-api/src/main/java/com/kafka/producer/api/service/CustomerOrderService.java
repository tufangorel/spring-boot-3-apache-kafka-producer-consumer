package com.kafka.producer.api.service;


import com.kafka.producer.api.model.CustomerOrder;
import com.kafka.producer.api.model.OrderItem;
import com.kafka.producer.api.repository.CustomerOrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerOrderService {

    private final CustomerOrderRepository customerOrderRepository;

    public CustomerOrderService(CustomerOrderRepository customerOrderRepository) {
        this.customerOrderRepository = customerOrderRepository;
    }

    @Transactional
    public CustomerOrder save(CustomerOrder customerOrder){

        for( OrderItem orderItem: customerOrder.getOrderItems() ) {
            orderItem.setCustomerOrder(customerOrder);
        }
        CustomerOrder result = customerOrderRepository.save(customerOrder);
        return result;
    }

    public List<CustomerOrder> findAll(){
        return customerOrderRepository.findAll();
    }

    @Transactional
    public void deleteCustomerOrder(Integer id) {
        customerOrderRepository.deleteById(id);
    }

    public Optional<CustomerOrder> findByID(Integer id) {
        return customerOrderRepository.findById(id);
    }
}