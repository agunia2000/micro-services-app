package com.comarch.microservices.customers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CustomersApplication {
		//TODO: spytac czemu sie baza tworzona przeze mnie dbevarze sama usuwa (hibernate??)
	//TODO: hashe dla tego samego hasla sa rozne?? dodac endpoint login moze?
	public static void main(String[] args) {
		SpringApplication.run(CustomersApplication.class, args);
	}

}
