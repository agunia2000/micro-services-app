package com.comarch.microservices.customers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CustomersApplication {
		//TODO: spytac czemu sie baza tworzona przeze mnie dbevarze sama usuwa (hibernate??)
	public static void main(String[] args) {
		SpringApplication.run(CustomersApplication.class, args);
	}

}
