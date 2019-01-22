package com.djorquab.relational.relational.managers;

import java.io.Serializable;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.djorquab.relational.relational.bo.User;
import com.djorquab.relational.relational.exceptions.EmailAlreadyRegisteredException;
import com.djorquab.relational.relational.model.AuthenticatedUser;
import com.djorquab.relational.relational.repositories.AuthenticatedUsersRepository;

@Component
public class UserAuthenticationManager implements UserDetailsService, Serializable {
	private static final long serialVersionUID = 5038557822597190569L;
	
	@Autowired
	private AuthenticatedUsersRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public AuthenticatedUser register(AuthenticatedUser user) throws EmailAlreadyRegisteredException {
		if (!repository.existsByEmail(user.getEmail())) {
			user.setLastModified(Calendar.getInstance().getTime());
			user.setCreatedOn(Calendar.getInstance().getTime());
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			return repository.save(user);
		}
		throw new EmailAlreadyRegisteredException("The email "+user.getEmail()+" is already used!");
	}

	@Override
	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException {
		AuthenticatedUser authenticatedUser = repository.findByEmail(email);
		if (authenticatedUser != null) {
			User user = new User();
			user.setUsername(authenticatedUser.getEmail());
			user.setPassword(authenticatedUser.getPassword());
			// TODO implement this: user.setRoles(roles);
			return user;
		}
		throw new UsernameNotFoundException(email);
	}
	
	public long countUsers() {
		return repository.count();
	}
	
	public void deleteUserByEmail(String email) {
		AuthenticatedUser user = repository.findByEmail(email);
		if (user != null) {
			repository.delete(user);
		}
	}
}
