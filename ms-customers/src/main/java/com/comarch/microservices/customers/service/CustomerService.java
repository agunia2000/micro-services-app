package com.comarch.microservices.customers.service;

import com.comarch.microservices.customers.model.Customer;
import com.comarch.microservices.customers.repository.CustomerRepository;
import com.comarch.microservices.customers.request.CustomerRequest;
import com.comarch.microservices.customers.utils.Security;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public void addCustomer(CustomerRequest request){
        Customer customer = Customer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(Security.hashPassword(request.getPassword()))
                .build();

        customerRepository.save(customer);
    }

    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    public void deleteCustomer(Long id){
        customerRepository.deleteById(id);
    }
}
