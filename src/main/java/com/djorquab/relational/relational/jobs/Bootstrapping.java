package com.djorquab.relational.relational.jobs;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.djorquab.relational.relational.exceptions.EmailAlreadyRegisteredException;
import com.djorquab.relational.relational.managers.PropertyManager;
import com.djorquab.relational.relational.managers.UserAuthenticationManager;
import com.djorquab.relational.relational.model.AuthenticatedUser;

@Component
public class Bootstrapping {
	@Autowired
	private PropertyManager propertyManager;
	
	@Autowired
	private UserAuthenticationManager userManager;
	
	@PostConstruct
	public void init() {
		if (propertyManager.isBootstrapping()) {
			AuthenticatedUser user = new AuthenticatedUser();
			user.setEmail("admin@test.com");
			user.setName("Administrator");
			user.setPassword("test");
			try {
				userManager.register(user);
			} catch (EmailAlreadyRegisteredException e) {

			}
		}
	}
}
