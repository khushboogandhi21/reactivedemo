package com.work.year22.reactivedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReactivedemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactivedemoApplication.class, args);
	}


	//actuator doesnt work with netty
}
