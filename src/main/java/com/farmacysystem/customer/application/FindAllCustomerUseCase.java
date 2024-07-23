package com.farmacysystem.customer.application;

import com.farmacysystem.customer.domain.entity.CustomerDto;
import com.farmacysystem.customer.domain.service.CustomeService.CustomerService;

import java.util.List;

public class FindAllCustomerUseCase {
    private final CustomerService customerService;

    public FindAllCustomerUseCase(CustomerService customerService) {
        this.customerService = customerService;
    }

    public List<CustomerDto> execute() {
        return customerService.findAllCustomer();
    }
}