package com.djorquab.relational.relational;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import com.djorquab.relational.relational.bo.User;
import com.djorquab.relational.relational.exceptions.EmailAlreadyRegisteredException;
import com.djorquab.relational.relational.managers.UserAuthenticationManager;
import com.djorquab.relational.relational.model.AuthenticatedUser;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserRegistrationTest {
	private static final String EMAIL_TEST_1 = "test@t.c";
	private static final String NAME_TEST_1 = "TEST von test";
	private static final String PASSWORD_1 = "test";
	
	private static final String EMAIL_TEST_2 = "aldebaran@testcompany.c";
	private static final String NAME_TEST_2 = "Aldebaran";
	private static final String PASSWORD_2 = "ald!b4r4n";
	
	@Autowired
	private UserAuthenticationManager manager;
	@Autowired
	private PasswordEncoder encoder;
	
	private AuthenticatedUser user(String email, String name, String password) {
		AuthenticatedUser user = new AuthenticatedUser();
		user.setCreatedOn(Calendar.getInstance().getTime());
		user.setLastModified(Calendar.getInstance().getTime());
		user.setEmail(email);
		user.setName(name);
		user.setPassword(encoder.encode(password));
		return user;
	}
	
	@Test
	public void registerUser() throws EmailAlreadyRegisteredException {
		AuthenticatedUser user = user(EMAIL_TEST_1, NAME_TEST_1, PASSWORD_1);
		manager.register(user);
	}
	
	@Test(expected = EmailAlreadyRegisteredException.class)
	public void registerUserTwoTimesError() throws EmailAlreadyRegisteredException {
		AuthenticatedUser user = user(EMAIL_TEST_1, NAME_TEST_1, PASSWORD_1);
		try {
			manager.register(user);
		} catch(EmailAlreadyRegisteredException e) {
			throw new RuntimeException(e);
		}
		manager.register(user);
	}
	
	@Test
	public void registerUserTwoTimesAndViewSize() throws EmailAlreadyRegisteredException {
		AuthenticatedUser user = user(EMAIL_TEST_1, NAME_TEST_1, PASSWORD_1);
		manager.register(user);
		user = user(EMAIL_TEST_2, NAME_TEST_2, PASSWORD_2);
		manager.register(user);
		Assert.assertEquals(2L, manager.countUsers());
	}
	
	@Test
	public void loadUser() throws EmailAlreadyRegisteredException {
		AuthenticatedUser auth = user(EMAIL_TEST_1, NAME_TEST_1, PASSWORD_1);
		manager.register(auth);
		User user = (User)manager.loadUserByUsername(EMAIL_TEST_1);
		Assert.assertEquals(user.getUsername(), auth.getEmail());
	}
	
	@Test(expected = UsernameNotFoundException.class)
	public void loadUserNotFound() throws EmailAlreadyRegisteredException {
		AuthenticatedUser auth = user(EMAIL_TEST_1, NAME_TEST_1, PASSWORD_1);
		manager.register(auth);
		User user = (User)manager.loadUserByUsername(EMAIL_TEST_2);
		Assert.assertNotEquals(user.getUsername(), auth.getEmail());
	}
	
	
	@Test
	public void twoDifferentUsers() throws EmailAlreadyRegisteredException {
		AuthenticatedUser auth = user(EMAIL_TEST_1, NAME_TEST_1, PASSWORD_1);
		manager.register(auth);
		auth = user(EMAIL_TEST_2, NAME_TEST_2, PASSWORD_2);
		manager.register(auth);
		User user = (User)manager.loadUserByUsername(EMAIL_TEST_2);
		Assert.assertNotEquals(user.getUsername(), EMAIL_TEST_1);
	}
}
