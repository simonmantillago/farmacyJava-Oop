package com.farmacysystem.customer.application;

import com.farmacysystem.customer.domain.entity.Customer;
import com.farmacysystem.customer.domain.service.CustomeService.CustomerService;

public class CreateCustomerUseCase {
    private final CustomerService customerService;

    public CreateCustomerUseCase(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void execute(Customer customer) {
        customerService.createCustomer(customer);
    }
}
