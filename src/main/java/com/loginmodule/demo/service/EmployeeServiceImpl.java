package com.loginmodule.demo.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.loginmodule.demo.entity.Employee;
import com.loginmodule.demo.entity.EmployeeModel;
import com.loginmodule.demo.entity.Role;
import com.loginmodule.demo.exceptions.ItemAlreadyExistsException;
import com.loginmodule.demo.repo.EmployeeRepo;
import com.loginmodule.demo.repo.RoleRepo;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepo employeeRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;

	@Override
	public Employee createEmployee(EmployeeModel employeeModel) {
		if (employeeRepo.existsByEmail(employeeModel.getEmail())) {
			throw new ItemAlreadyExistsException("User already Registerd with email :" + employeeModel.getEmail());
		}
		Employee employee = new Employee();

		/*
		 * employee.setName(employeeModel.getName());
		 * employee.setEmail(employeeModel.getEmail());
		 * employeeModel.setPassword(passwordEncoder.encode(employeeModel.getPassword())
		 * );
		 */

		EmployeeModel model = new EmployeeModel();
		model.setEmail(employeeModel.getEmail());
		model.setPassword(passwordEncoder.encode(employeeModel.getPassword()));
		model.setName(employeeModel.getName());

		BeanUtils.copyProperties(model, employee);
		
		employee.setEnabled(true);

		Long id = 2L;
		Role role = roleRepo.findById(id).orElseThrow();
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		employee.setRoles(roles);

		return employeeRepo.save(employee);
	}

}
