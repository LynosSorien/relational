package com.djorquab.relational.relational.utils;

import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;

import com.djorquab.relational.relational.bo.User;
import com.djorquab.relational.relational.commons.UserDTO;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class BackofficeUtils {
	
	public static final ModelAndView createModelAndView(String template, Object ... params) {
		User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Map<String, Object> mapParams = null;
		if (params == null || params.length == 0 || params.length%2 != 0) {
			mapParams = Utils.createParams("user", UserDTO.builder().username(authUser.getUsername()).build());
		} else {
			mapParams = Utils.createParams("user", UserDTO.builder().username(authUser.getUsername()).build(), params);
		}
		log.info("Map params {}", mapParams);
		return new ModelAndView(template,
				mapParams);
	}
}
