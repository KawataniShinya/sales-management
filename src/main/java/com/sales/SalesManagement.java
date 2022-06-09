package com.sales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SalesManagement {

	public static void main(String[] args) {
		SpringApplication.run(SalesManagement.class, args);
	}

}
