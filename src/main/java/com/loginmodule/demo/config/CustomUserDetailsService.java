package com.loginmodule.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.loginmodule.demo.entity.Employee;
import com.loginmodule.demo.exceptions.ResourceNotFoundException;
import com.loginmodule.demo.repo.EmployeeRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private EmployeeRepo employeeRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Employee existingEmp = employeeRepo.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("Employee not Found with email :"+email));

		return new CustomUserDetails(existingEmp);
	}

}
