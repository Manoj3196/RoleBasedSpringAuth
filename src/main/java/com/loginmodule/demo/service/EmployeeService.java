package com.loginmodule.demo.service;

import com.loginmodule.demo.entity.Employee;
import com.loginmodule.demo.entity.EmployeeModel;
import com.loginmodule.demo.entity.Role;

public interface EmployeeService {

	Employee createEmployee(EmployeeModel employeeModel);

}