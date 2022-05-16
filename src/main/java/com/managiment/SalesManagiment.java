package com.managiment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SalesManagiment {

	@RequestMapping("/")
	String index(){
		return "Hello World!";
	}

	public static void main(String[] args) {

		SpringApplication.run(SalesManagiment.class, args);

	}

}
