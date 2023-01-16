package com.loginmodule.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	
	@GetMapping("/hi")
	public String demo()
	{
		return "Hi Employee";
	}

}
