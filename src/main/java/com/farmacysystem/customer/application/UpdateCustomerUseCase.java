package com.farmacysystem.customer.application;

import com.farmacysystem.customer.domain.entity.Customer;
import com.farmacysystem.customer.domain.service.CustomeService.CustomerService;

public class UpdateCustomerUseCase {
    private final CustomerService customerService;

    public UpdateCustomerUseCase(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void execute(Customer customer){
        customerService.updateCustomer(customer);
    }
}