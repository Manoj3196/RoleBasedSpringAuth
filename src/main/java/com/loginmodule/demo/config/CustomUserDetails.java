package com.loginmodule.demo.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.loginmodule.demo.entity.Employee;
import com.loginmodule.demo.entity.Role;

public class CustomUserDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Employee employee;

	public CustomUserDetails(Employee emp) {
		this.employee = emp;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Set<Role> roles = employee.getRoles();
		
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();

		for(Role r : roles)
		{
			authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
		}
		
		return authorities;
	}

	@Override
	public String getPassword() {

		return employee.getPassword();
	}

	@Override
	public String getUsername() {

		return employee.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return employee.getEnabled();
	}

}
