package com.djorquab.relational.relational.web.admin;

import com.djorquab.relational.relational.commons.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.djorquab.relational.relational.BackofficeConstants;
import com.djorquab.relational.relational.bo.PersonBO;
import com.djorquab.relational.relational.commons.PagedResult;
import com.djorquab.relational.relational.forms.PersonForm;
import com.djorquab.relational.relational.services.PeopleService;
import com.djorquab.relational.relational.utils.BackofficeUtils;

import java.io.Serializable;

@RestController
@RequestMapping("/backoffice/people")
public class PeopleBackofficeController {
	@Autowired
	private PeopleService service;
	
	@GetMapping
	public ModelAndView people() {
		PagedResult<PersonBO> result = service.findAllPaged(0, 10);
		return BackofficeUtils.createModelAndViewWithTableDefinition("config/people",
				PersonBO.class,
				"tableResult", result);
	}
	
	@GetMapping("/paging")
	public ModelAndView paging(
			@RequestParam(name = "page", defaultValue= "1") int page,
			@RequestParam(name = "size", defaultValue = "10") int size,
			@RequestParam(name = BackofficeConstants.ID, required = false) String id) {
		PagedResult<PersonBO> result = service.findAllPaged(page-1, size);
		return BackofficeUtils.fragmentWithTableDefinition("fragments/tables :: simpleTable",
				PersonBO.class,
				"pagedEndpoint", "/backoffice/people/paging", // FIXME ? - View if this is required or we can obtain it using jquery
				"tableResult", result,
				BackofficeConstants.ID, id);
	}
	
	@GetMapping("/creation")
	public ModelAndView creation(@RequestParam(name = "personId", required = false) Long personId) {
		PersonBO person = null;
		if (personId != null) {
			person = service.getById(personId);
		}
		return BackofficeUtils.createModelAndViewWithFormDefinition("config/people/creation", PersonForm.class, "person", person != null ? person : PersonBO.builder().build());
	}
	
	@GetMapping("/edit")
	public ModelAndView editPage(@RequestParam("personId") Long personId) {
		return creation(personId);
	}

	@DeleteMapping
	public ModelAndView remove(@RequestParam("personId") Long personId) {
		service.delete(personId);
		return paging(1, 10, "relational-table");
	}

}
