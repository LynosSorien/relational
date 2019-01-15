package com.djorquab.relational.relational.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;

import com.djorquab.relational.relational.bo.User;
import com.djorquab.relational.relational.commons.UserDTO;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BackofficeUtils {
	
	public static final ModelAndView createModelAndView(String template, Object ... params) {
		User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return new ModelAndView(template,
				Utils.createParams(params, "user", UserDTO.builder().username(authUser.getUsername()).build()));
	}
}
