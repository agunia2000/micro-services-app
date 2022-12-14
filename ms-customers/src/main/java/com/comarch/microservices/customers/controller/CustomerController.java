package com.comarch.microservices.customers.controller;

import com.comarch.microservices.customers.model.Customer;
import com.comarch.microservices.customers.request.CustomerRequest;
import com.comarch.microservices.customers.request.LoginRequest;
import com.comarch.microservices.customers.response.CustomerResponse;
import com.comarch.microservices.customers.service.CustomerService;
import com.comarch.microservices.customers.utils.JwtUtil;
import com.comarch.microservices.customers.utils.Security;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<CustomerResponse> registerCustomer(@RequestBody CustomerRequest request){

        Long customerId = customerService.addCustomer(request);
        log.info("New customer with login '" + request.getEmail() +  "' was added");

        return new ResponseEntity<>(new CustomerResponse(customerId,request.getFirstName(),request.getLastName(),
                request.getEmail(), Security.hashPassword(request.getPassword())), HttpStatus.CREATED);
    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getCustomers(){
        return new ResponseEntity<>(customerService.getCustomers(),HttpStatus.OK);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") Long customerId){
        customerService.deleteCustomer(customerId);
        return new ResponseEntity<>("Successfully Deleted Customer with id: " + customerId, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> verifyLoginData(@RequestBody LoginRequest request){
        if(customerService.customerDataCorrect(request.getEmail(), request.getPassword())){
            String token = jwtUtil.generateToken(request.getEmail());
            return new ResponseEntity<>(token, HttpStatus.CREATED);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/customers/{email}")
    public ResponseEntity<Customer> getCustomerByEmail(@PathVariable("email") String email){
        return new ResponseEntity<>(customerService.getCustomerByEmail(email),HttpStatus.OK);
    }
}
