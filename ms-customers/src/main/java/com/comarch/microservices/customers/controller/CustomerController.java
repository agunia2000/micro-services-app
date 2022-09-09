package com.comarch.microservices.customers.controller;

import com.comarch.microservices.customers.model.Customer;
import com.comarch.microservices.customers.request.CustomerRequest;
import com.comarch.microservices.customers.request.LoginRequest;
import com.comarch.microservices.customers.response.CustomerResponse;
import com.comarch.microservices.customers.service.CustomerService;
import com.comarch.microservices.customers.utils.Security;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/customers")
    public ResponseEntity<CustomerResponse> registerCustomer(@RequestBody CustomerRequest request){

        customerService.addCustomer(request);
        log.info("New customer with login '" + request.getEmail() +  "' was added");

        return ResponseEntity.ok(new CustomerResponse(request.getFirstName(),request.getLastName(),
                request.getEmail(), Security.hashPassword(request.getPassword())));
    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getCustomers(){
        return ResponseEntity.ok(customerService.getCustomers());
    }

    @DeleteMapping("/customers/{id}")
    public String deleteCustomer(@PathVariable("id") Long customerId){
        customerService.deleteCustomer(customerId);
        return "Successfully Deleted Customer with id: " + customerId;
    }

    @PostMapping("/customers/login")
    public String verifyLoginData(@RequestBody LoginRequest request){
        if(customerService.customerDataCorrect(request.getEmail(), request.getPassword())){
            return "Correct data!";
        }
        return "Invalid Data, Try Again!";
    }
}
