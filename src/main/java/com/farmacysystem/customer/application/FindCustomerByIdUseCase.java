package com.farmacysystem.customer.application;


import com.farmacysystem.customer.domain.entity.CustomerDto;
import com.farmacysystem.customer.domain.service.CustomeService.CustomerService;

import java.util.Optional;

public class FindCustomerByIdUseCase {
    private final CustomerService customerService;

    public FindCustomerByIdUseCase(CustomerService customerService) {
        this.customerService = customerService;
    }

    public Optional<CustomerDto> execute(String id) {
        return customerService.findCustomerById(id);
    }
}