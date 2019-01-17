package com.djorquab.relational.relational.web.admin;

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
	private RelationNameService relationNamesService;
	
	@GetMapping
	public ModelAndView backoffice() {
		return BackofficeUtils.createModelAndView("backoffice");
	}
	
	@GetMapping("/relational/names")
	public ModelAndView relationalNames() {
		PagedResult<RelationNameBO> result = relationNamesService.findAllPaged(0, 10);
		log.info("Relation names found {}", result);
		return BackofficeUtils.createModelAndViewWithTableDefinition("config/relationalNames", RelationNameBO.class, "tableResult", result);
	}
	
	@GetMapping("/relational/names/paging")
	public ModelAndView pagingRelationalNames(@RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
		page = page-1;
		PagedResult<RelationNameBO> result = relationNamesService.findAllPaged(page, size);
		return BackofficeUtils.fragmentWithTableDefinition("fragments/tables :: pagedTable", 
				RelationNameBO.class,
				"pagedEndpoint", "/backoffice/relational/names/paging",
				"tableResult", result);
	}
}
