package com.djorquab.relational.relational.jobs;

import javax.annotation.PostConstruct;

import com.djorquab.relational.relational.bo.PersonBO;
import com.djorquab.relational.relational.services.PeopleService;
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

import java.util.Date;

@Component
public class Bootstrapping {
	@Value("${admin.email:}")
	@Getter
	private String adminEmail;
	
	@Autowired
	private PropertyManager propertyManager;
	
	@Autowired
	private UserAuthenticationManager userManager;
	
	@Autowired
	private RelationNameService relationNameService;

	@Autowired
	private PeopleService peopleService;
	
	@PostConstruct
	public void init() {
		if (propertyManager.isBootstrapping()) {
			putUsers();
			putRelations();
			populatePeople();
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

	private void populatePeople() {
		peopleService.save(createPerson("Claire", "Farron", "http://pm1.narvii.com/6366/4eb03447499b4c297bf995782545f1621041f909_00.jpg", "Main character of FFXIII series", null, null));
		peopleService.save(createPerson("Link", null, "https://i.kym-cdn.com/photos/images/facebook/001/278/235/d9c.jpg", "Playable character of The legend of Zelda series", null, null));

	}

	private PersonBO createPerson(String name, String surname, String image, String description, Date birth, Date death) {
		return PersonBO.builder().name(name).surname(surname).image(image).description(description).birthday(birth).deathdate(death).build();
	}
}
