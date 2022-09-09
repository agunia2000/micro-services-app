package com.comarch.microservices.customers.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    String email;
    String password;
}
