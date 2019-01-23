package com.djorquab.relational.relational.jobs;

import javax.annotation.PostConstruct;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.djorquab.relational.relational.bo.RelationNameBO;
import com.djorquab.relational.relational.exceptions.EmailAlreadyRegisteredException;
import com.djorquab.relational.relational.managers.PropertyManager;
import com.djorquab.relational.relational.managers.UserAuthenticationManager;
import com.djorquab.relational.relational.model.AuthenticatedUser;
import com.djorquab.relational.relational.services.RelationNameService;

@Component
public class Bootstrapping {
	@Value("${admin.email}")
	@Getter
	private String adminEmail;
	
	@Autowired
	private PropertyManager propertyManager;
	
	@Autowired
	private UserAuthenticationManager userManager;
	
	@Autowired
	private RelationNameService relationNameService;
	
	@PostConstruct
	public void init() {
		if (propertyManager.isBootstrapping()) {
			putUsers();
			putRelations();
		}
	}
	
	private void putUsers() {
		AuthenticatedUser user = new AuthenticatedUser();
		user.setEmail(getAdminEmail());
		user.setName("Administrator");
		user.setPassword("test");
		try {
			userManager.register(user);
		} catch (EmailAlreadyRegisteredException e) {

		}
	}
	
	private void putRelations() {
		relationNameService.save(RelationNameBO.builder().name("Sister").build());
		relationNameService.save(RelationNameBO.builder().name("Brother").build());
		relationNameService.save(RelationNameBO.builder().name("Friend").build());
		relationNameService.save(RelationNameBO.builder().name("Kills").build());
		relationNameService.save(RelationNameBO.builder().name("Saves").build());
		relationNameService.save(RelationNameBO.builder().name("Killed by/on").build());
		relationNameService.save(RelationNameBO.builder().name("Teaches").build());
		relationNameService.save(RelationNameBO.builder().name("Student").build());
	}
}
