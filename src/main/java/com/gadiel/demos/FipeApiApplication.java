package com.gadiel.demos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FipeApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FipeApiApplication.class, args);
	}

}
