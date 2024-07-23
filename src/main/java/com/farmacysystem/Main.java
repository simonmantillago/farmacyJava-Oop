package com.farmacysystem;

import com.farmacysystem.customer.application.CreateCustomerUseCase;
import com.farmacysystem.customer.application.DeleteCustomerUseCase;
import com.farmacysystem.customer.application.FindAllCustomerUseCase;
import com.farmacysystem.customer.application.FindCustomerByIdUseCase;
import com.farmacysystem.customer.application.UpdateCustomerUseCase;
import com.farmacysystem.customer.domain.service.CustomeService.CustomerService;
import com.farmacysystem.customer.infrastructure.CustomerRepository;
import com.farmacysystem.customer.infrastructure.CustomerUi.CustomerCrudUi;

public class Main {
    public static void main(String[] args) {
        CustomerService customerService = new CustomerRepository();
        CreateCustomerUseCase createCustomerUseCase = new CreateCustomerUseCase(customerService);
        FindCustomerByIdUseCase findCustomerByIdUseCase = new FindCustomerByIdUseCase(customerService);
        UpdateCustomerUseCase updateCustomerUseCase = new UpdateCustomerUseCase(customerService);
        DeleteCustomerUseCase deleteCustomerUseCase = new DeleteCustomerUseCase(customerService);
        FindAllCustomerUseCase findAllCustomerUseCase = new FindAllCustomerUseCase(customerService);
        

        CustomerCrudUi customerCrudUi = new CustomerCrudUi(createCustomerUseCase, findCustomerByIdUseCase, updateCustomerUseCase,deleteCustomerUseCase,findAllCustomerUseCase);
        customerCrudUi.showCrudOptions();
    }
}
