package com.loginmodule.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoginApplication {
	


	public static void main(String[] args) {
		SpringApplication.run(LoginApplication.class, args);
		System.out.println("Server Started");
		
		
	}

}
