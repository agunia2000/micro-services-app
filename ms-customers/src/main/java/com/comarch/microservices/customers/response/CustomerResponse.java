package com.comarch.microservices.customers.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CustomerResponse {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String hashedPassword;
}
