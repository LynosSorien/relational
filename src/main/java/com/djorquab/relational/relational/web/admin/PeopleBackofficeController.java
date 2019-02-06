package com.djorquab.relational.relational.web.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.djorquab.relational.relational.BackofficeConstants;
import com.djorquab.relational.relational.bo.PersonBO;
import com.djorquab.relational.relational.commons.PagedResult;
import com.djorquab.relational.relational.forms.PersonForm;
import com.djorquab.relational.relational.services.PeopleService;
import com.djorquab.relational.relational.utils.BackofficeUtils;

@RestController
@RequestMapping("/backoffice/people")
@Slf4j
public class PeopleBackofficeController {
	@Autowired
	private PeopleService service;

	@PostMapping
	public ModelAndView createPerson(@ModelAttribute(BackofficeConstants.FORM_OBJECT) PersonBO person) {
		service.save(person);
		return people("The request has processed correctly");
	}
	
	@GetMapping
	public ModelAndView people(String successMessage) {
		PagedResult<PersonBO> result = service.findAllPaged(0, 10);
		return BackofficeUtils.createModelAndViewWithTableDefinition("config/people",
				PersonBO.class,
				"tableResult", result,
				BackofficeConstants.GLOBAL_SUCCESS_MESSAGE, successMessage);
	}
	
	@GetMapping("/paging")
	public ModelAndView paging(
			@RequestParam(name = "page", defaultValue= "1") int page,
			@RequestParam(name = "size", defaultValue = "10") int size,
			@RequestParam(name = BackofficeConstants.ID, defaultValue = "") String id) {
		PagedResult<PersonBO> result = service.findAllPaged(page-1, size);
		log.info("Paged result {}", result);
		return BackofficeUtils.fragmentWithTableDefinition("fragments/tables :: pagedTable",
				PersonBO.class,
				"pagedEndpoint", "/backoffice/people/paging", // FIXME ? - View if this is required or we can obtain it using jquery
				BackofficeConstants.TABLE_RESULT, result,
				BackofficeConstants.ID, id,
				BackofficeConstants.GLOBAL_SUCCESS_MESSAGE, "Person deleted correctly");
	}
	
	@GetMapping("/creation")
	public ModelAndView creation(@RequestParam(name = "personId", required = false) Long personId) {
		PersonBO person = null;
		if (personId != null) {
			person = service.getById(personId);
		}
		return BackofficeUtils.createModelAndViewWithFormDefinition("config/people/creation", PersonForm.class,
				"person", person != null ? person : PersonBO.builder().build(),
				BackofficeConstants.FORM_OBJECT, PersonBO.builder().build());
	}
	
	@GetMapping("/edit")
	public ModelAndView editPage(@RequestParam("personId") Long personId) {
		return creation(personId);
	}

	@DeleteMapping
	public ModelAndView remove(@RequestParam("personId") Long personId, @RequestParam(name = "id", required = false) String htmlId) {
		service.delete(personId);
		return paging(1, 10, htmlId);
	}

}
