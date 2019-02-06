package com.djorquab.relational.relational.web.admin;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.djorquab.relational.relational.BackofficeConstants;
import com.djorquab.relational.relational.bo.RelationNameBO;
import com.djorquab.relational.relational.commons.PagedResult;
import com.djorquab.relational.relational.services.RelationNameService;
import com.djorquab.relational.relational.utils.BackofficeUtils;

@RestController
@RequestMapping("/backoffice/relational/names")
@Slf4j
public class RelationNamesBackofficeController {
	@Autowired
	private RelationNameService service;
	
	@GetMapping
	public ModelAndView relationalNames(String success, String error) {
		PagedResult<RelationNameBO> result = service.findAllPaged(0, 10);
		log.debug("Relation names found {}", result);
		return BackofficeUtils.createModelAndViewWithTableDefinitionAndForm("config/relationalNames", RelationNameBO.class, RelationNameBO.class,
				"tableResult", result,
				BackofficeConstants.FORM_OBJECT, RelationNameBO.builder().build(),
				BackofficeConstants.GLOBAL_SUCCESS_MESSAGE, success,
				BackofficeConstants.GLOBAL_ERROR_MESSAGE, error);
	}
	
	@GetMapping("/paging")
	public ModelAndView pagingRelationalNames(@RequestParam(name = "page", defaultValue = "1") int page, 
			@RequestParam(name = "size", defaultValue = "10") int size,
			@RequestParam(name = BackofficeConstants.ID, defaultValue = "") String id) {
		page = page-1;
		PagedResult<RelationNameBO> result = service.findAllPaged(page, size);
		return BackofficeUtils.fragmentWithTableDefinition("fragments/tables :: pagedTable", 
				RelationNameBO.class,
				"pagedEndpoint", "/backoffice/relational/names/paging",
				"tableResult", result,
				BackofficeConstants.ID, id);
	}

	@PostMapping
	public ModelAndView create(@ModelAttribute(BackofficeConstants.FORM_OBJECT) RelationNameBO relationName) {
		log.info("Adding new relation... {}", relationName);
		relationName.setName(relationName.getName().trim());
		if (!service.existsRelationWithName(relationName.getName())) {
			service.save(relationName);
			return relationalNames("The relation has been added correctly", null);
		} else {
			return relationalNames(null, "The relation already exists!");
		}
	}
	
	@DeleteMapping
	public ModelAndView relationalName(@RequestParam(name = "relationId") long relationId,
			@RequestParam(name = "id", defaultValue = "") String htmlId) {
		service.delete(relationId);
		return pagingRelationalNames(1, 10, htmlId);
	}
}
