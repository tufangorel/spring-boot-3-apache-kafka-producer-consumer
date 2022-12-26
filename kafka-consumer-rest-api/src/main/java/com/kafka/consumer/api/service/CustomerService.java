package com.kafka.consumer.api.service;


import com.kafka.consumer.api.model.Customer;
import com.kafka.consumer.api.repository.CustomerRepository;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class.getName());

    private PlatformTransactionManager customTransactionManager;
    private final EntityManager entityManager;
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository, EntityManager entityManager,
                           PlatformTransactionManager customTransactionManager) {
        this.customerRepository = customerRepository;
        this.entityManager = entityManager;
        this.customTransactionManager=customTransactionManager;
    }

    @Transactional
    public Customer save(Customer customer){
        return customerRepository.save(customer);
    }

    public Customer saveWithProgrammaticTransactionManagement(Customer customer){

        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
        definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        definition.setReadOnly(true);
        definition.setTimeout(1000);
        definition.setName("#programmaticTransaction#");

        TransactionStatus status = customTransactionManager.getTransaction(definition);

        try {
            entityManager.persist(customer);
            entityManager.flush();
            customTransactionManager.commit(status);
            LOGGER.info("Persisted entity : "+customer);
        }
        catch (Exception exception) {
            customTransactionManager.rollback(status);
            throw exception;
        }
        return customer;
    }

    public List<Customer> findAllByJPARepository(){
        Iterable<Customer> result = customerRepository.findAll();
        return (List<Customer>) result;
    }

    @Transactional
    public void deleteCustomer(Integer id) {
        customerRepository.deleteById(id);
    }

    public Optional<Customer> findCustomerByID(Integer id) {
        return customerRepository.findById(id);
    }

}