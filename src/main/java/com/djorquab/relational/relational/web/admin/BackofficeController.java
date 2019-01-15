package com.djorquab.relational.relational.web.admin;

import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.djorquab.relational.relational.utils.BackofficeUtils;
import com.djorquab.relational.relational.utils.Utils;

@RestController
@RequestMapping("/backoffice")
@Slf4j
@SuppressWarnings("unused")
public class BackofficeController {
	
	@GetMapping
	public ModelAndView backoffice() {
		return BackofficeUtils.createModelAndView("backoffice");
	}
}
