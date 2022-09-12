package com.comarch.microservices.customers.service;

import com.comarch.microservices.customers.model.Customer;
import com.comarch.microservices.customers.repository.CustomerRepository;
import com.comarch.microservices.customers.request.CustomerRequest;
import com.comarch.microservices.customers.utils.Security;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Long addCustomer(CustomerRequest request){
        Customer customer = Customer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(Security.hashPassword(request.getPassword()))
                .build();

        return customerRepository.save(customer).getId();
    }

    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    public void deleteCustomer(Long id){
        customerRepository.deleteById(id);
    }

    public boolean customerDataCorrect(String email, String password){
        Customer customer = customerRepository.findByEmail(email);

        return customer != null && BCrypt.checkpw(password.getBytes(StandardCharsets.UTF_8),customer.getPassword());
    }

    public Customer getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }
}
