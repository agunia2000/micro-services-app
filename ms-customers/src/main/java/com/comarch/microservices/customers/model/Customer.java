package com.comarch.microservices.customers.model;

import com.comarch.microservices.customers.utils.Security;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CUSTOMERS",uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @Pattern(regexp = Security.EMAIL_REGEX, message = "Incorrect email! Try Again!")
    private String email;
    @Size(min = 5)
    @NotNull
    private String password;
}
