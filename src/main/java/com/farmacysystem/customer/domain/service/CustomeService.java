package com.farmacysystem.customer.domain.service;

import java.util.List;
import java.util.Optional;

import com.farmacysystem.customer.domain.entity.Customer;
import com.farmacysystem.customer.domain.entity.CustomerDto;


public class CustomeService {
    public interface CustomerService {
        void createCustomer(Customer Customer);
        void updateCustomer(Customer Customer);
        Customer deleteCustomer(String id);
        Optional<CustomerDto> findCustomerById(String id);
        List<CustomerDto> findAllCustomer();
        
    }
}
