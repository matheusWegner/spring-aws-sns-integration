package com.matheuswegner.newtestbackendspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class NewTestBackendSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewTestBackendSpringApplication.class, args);
	}

}
