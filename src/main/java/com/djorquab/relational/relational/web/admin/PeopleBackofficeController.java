package com.djorquab.relational.relational.web.admin;

import com.djorquab.relational.relational.components.tables.PeopleTable;
import com.djorquab.relational.relational.managers.PeopleManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.djorquab.relational.relational.BackofficeConstants;
import com.djorquab.relational.relational.bo.PersonBO;
import com.djorquab.relational.relational.commons.PagedResult;
import com.djorquab.relational.relational.components.forms.PersonForm;
import com.djorquab.relational.relational.services.PeopleService;
import com.djorquab.relational.relational.utils.BackofficeUtils;

@RestController
@RequestMapping("/backoffice/people")
@Slf4j
public class PeopleBackofficeController {
	@Autowired
	private PeopleManager manager;

	@Autowired
	private PeopleService service;

	@PostMapping
	public ModelAndView createPerson(@ModelAttribute(BackofficeConstants.FORM_OBJECT) PersonBO person) {
		manager.create(person);
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
		return BackofficeUtils.fragmentWithTableDefinition("fragments/tables :: selectablePagedTable",
				PersonBO.class,
				"pagedEndpoint", "/backoffice/people/paging", // FIXME ? - View if this is required or we can obtain it using jquery
				BackofficeConstants.TABLE_RESULT, result,
				BackofficeConstants.ID, id,
				BackofficeConstants.GLOBAL_SUCCESS_MESSAGE, "Person deleted correctly");
	}

	@GetMapping("/find/by")
	public ModelAndView search(@RequestParam(name = "name", required = false) String name,
							   @RequestParam(name = "surname", required = false) String surname,
							   @RequestParam(name = "page", defaultValue = "1") int page,
							   @RequestParam(name = "size", defaultValue = "10") int size,
							   @RequestParam(name = BackofficeConstants.ID, defaultValue = "") String id) {
		log.info("New request to search by {}, {}", name, surname);
		return BackofficeUtils.fragmentWithTableDefinition("fragments/tables :: selectablePagedTable",
				PeopleTable.class,
				"pagedEndpoint", "/backoffice/people/find/by/paging",
				"tableClass", "selectable-table",
				BackofficeConstants.TABLE_RESULT, service.findPaged(name, surname, page-1, size),
				BackofficeConstants.ID, id);
	}

	@GetMapping("/find/by/paging")
	public ModelAndView searchPaging(@RequestParam(name = "name", required = false) String name,
							   @RequestParam(name = "surname", required = false) String surname,
							   @RequestParam(name = "page", defaultValue = "1") int page,
							   @RequestParam(name = "size", defaultValue = "10") int size,
							   @RequestParam(name = BackofficeConstants.ID, defaultValue = "") String id) {
		return search(name, surname, page, size, id);
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
		manager.delete(personId);
		return paging(1, 10, htmlId);
	}

}
