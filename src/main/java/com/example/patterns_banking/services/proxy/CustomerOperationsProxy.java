package com.example.patterns_banking.services.proxy;

import com.example.patterns_banking.dtos.CustomerDTO;

import com.example.patterns_banking.models.Customer;
import com.example.patterns_banking.repositories.ICustomerRepository;
import com.example.patterns_banking.services.commands.CreateCustomerCommand;
import com.example.patterns_banking.services.commands.ICommand;
import org.springframework.stereotype.Component;

@Component
public class CustomerOperationsProxy implements ICustomerOperations {

    private final ICustomerRepository customerRepository;

    public CustomerOperationsProxy(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createCustomer(CustomerDTO customerDTO) {
        validationEmail(customerDTO.getEmail());
        ICommand<Customer> command = new CreateCustomerCommand(customerRepository, customerDTO);
        return command.execute();
    }

    public void validationEmail(String email) {
        if (email.toLowerCase().endsWith("@yahoo.com")) {
            throw new IllegalArgumentException("No se permiten correos del dominio Yahoo.");
        }
    }
}
