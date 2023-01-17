package com.loginmodule.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loginmodule.demo.config.CustomUserDetailsService;
import com.loginmodule.demo.config.JwtUtil;
import com.loginmodule.demo.entity.Employee;
import com.loginmodule.demo.entity.EmployeeModel;
import com.loginmodule.demo.entity.JwtResponse;
import com.loginmodule.demo.entity.LoginModel;
import com.loginmodule.demo.service.EmployeeService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@PostMapping("/logins")
	public ResponseEntity<?> getLogin(@RequestBody LoginModel loginModel) throws Exception {

		Authentication authentication;
		try {
			authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginModel.getEmail(), loginModel.getPassword()));

			// SecurityContextHolder.getContext().setAuthentication(authentication);
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
		final String token = jwtTokenUtil.generateToken(authentication);

		return new ResponseEntity<>(new JwtResponse(token), HttpStatus.OK);
	}

	@PostMapping("/register")
	public ResponseEntity<Employee> createUser(@Valid @RequestBody EmployeeModel employeeModel) {

		return new ResponseEntity<Employee>(employeeService.createEmployee(employeeModel), HttpStatus.CREATED);

	}

}
