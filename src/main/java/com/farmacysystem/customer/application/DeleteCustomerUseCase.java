package com.farmacysystem.customer.application;

import com.farmacysystem.customer.domain.entity.Customer;
import com.farmacysystem.customer.domain.service.CustomeService.CustomerService;

public class DeleteCustomerUseCase {
    private final CustomerService customerService;

    public DeleteCustomerUseCase(CustomerService customerService) {
        this.customerService = customerService;
    }

    public Customer execute(String customerId) {
        return customerService.deleteCustomer(customerId);
    }
}