package com.djorquab.relational.relational.managers;

import java.io.Serializable;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.djorquab.relational.relational.exceptions.EmailAlreadyRegisteredException;
import com.djorquab.relational.relational.model.AuthenticatedUser;
import com.djorquab.relational.relational.repositories.AuthenticatedUsersRepository;

@Component
public class UserAuthenticationManager implements Serializable {
	private static final long serialVersionUID = 5038557822597190569L;
	
	@Autowired
	private AuthenticatedUsersRepository repository;
	
	public AuthenticatedUser register(AuthenticatedUser user) throws EmailAlreadyRegisteredException {
		if (!repository.existsByEmail(user.getEmail())) {
			user.setLastModified(Calendar.getInstance().getTime());
			user.setCreatedOn(Calendar.getInstance().getTime());
			return repository.save(user);
		}
		throw new EmailAlreadyRegisteredException("The email "+user.getEmail()+" is already used!");
	}
	
}
