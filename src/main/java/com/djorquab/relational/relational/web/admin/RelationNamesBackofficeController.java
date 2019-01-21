package com.djorquab.relational.relational.web.admin;

import java.io.Serializable;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.djorquab.relational.relational.bo.RelationNameBO;
import com.djorquab.relational.relational.commons.PagedResult;
import com.djorquab.relational.relational.commons.ResponseDTO;
import com.djorquab.relational.relational.services.RelationNameService;
import com.djorquab.relational.relational.utils.BackofficeUtils;

@RestController
@RequestMapping("/backoffice/relational/names")
@Slf4j
public class RelationNamesBackofficeController {
	@Autowired
	private RelationNameService service;
	
	@PostMapping
	public ResponseEntity<ResponseDTO<Serializable>> save(@RequestBody RelationNameBO request) {
		try {
			service.save(request);
			return ResponseEntity.ok(ResponseDTO.builder().message("The relation "+request.getName()+" has been saved!").build());
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseDTO.builder().message(e.getLocalizedMessage()).build());
		}
	}
	
	@GetMapping
	public ModelAndView relationalNames() {
		PagedResult<RelationNameBO> result = service.findAllPaged(0, 10);
		log.info("Relation names found {}", result);
		return BackofficeUtils.createModelAndViewWithTableDefinitionAndForm("config/relationalNames", RelationNameBO.class, RelationNameBO.class, "tableResult", result);
	}
	
	@GetMapping("/paging")
	public ModelAndView pagingRelationalNames(@RequestParam(name = "page", defaultValue = "1") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
		page = page-1;
		PagedResult<RelationNameBO> result = service.findAllPaged(page, size);
		return BackofficeUtils.fragmentWithTableDefinition("fragments/tables :: pagedTable", 
				RelationNameBO.class,
				"pagedEndpoint", "/backoffice/relational/names/paging",
				"tableResult", result);
	}
}
