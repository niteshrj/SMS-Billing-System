package com.example.smsbillingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.example.smsbillingsystem")
public class SmsBillingSystemApplication {
	public static void main(String[] args) {
		SpringApplication.run(SmsBillingSystemApplication.class, args);
	}
}
