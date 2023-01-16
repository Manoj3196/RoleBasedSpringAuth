package com.loginmodule.demo.repo;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loginmodule.demo.entity.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

	Optional<Employee> findByEmail(String email);

	boolean existsByEmail( String email);

}
