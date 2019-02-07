package com.djorquab.relational.relational.web.admin;

import com.djorquab.relational.relational.services.ErrorJMSService;
import com.djorquab.relational.relational.services.PeopleService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.djorquab.relational.relational.bo.RelationNameBO;
import com.djorquab.relational.relational.commons.PagedResult;
import com.djorquab.relational.relational.services.RelationNameService;
import com.djorquab.relational.relational.utils.BackofficeUtils;
import com.djorquab.relational.relational.utils.Utils;

@RestController
@RequestMapping("/backoffice")
@Slf4j
@SuppressWarnings("unused")
public class BackofficeController {
	@Autowired
	private ErrorJMSService errorJMSService;

	@Autowired
	private PeopleService peopleService;

	@Autowired
	private RelationNameService relationNameService;

	@GetMapping
	public ModelAndView backoffice() {
		return BackofficeUtils.createModelAndView("backoffice",
				"jmsErrors", errorJMSService.count(),
				"relationNamesCount", relationNameService.count(),
				"peopleCount", peopleService.count());
	}
}
