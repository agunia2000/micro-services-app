package com.comarch.microservices.transactions.response;

import lombok.Getter;

@Getter
public class CustomerResponse {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String hashedPassword;
}
