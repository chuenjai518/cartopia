package com.uow.cartopia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="com.uow.*")
public class CartopiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartopiaApplication.class, args);
	}

}

