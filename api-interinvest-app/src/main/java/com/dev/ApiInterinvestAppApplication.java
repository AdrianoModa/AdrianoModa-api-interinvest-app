package com.dev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ApiInterinvestAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiInterinvestAppApplication.class, args);
	}

}
