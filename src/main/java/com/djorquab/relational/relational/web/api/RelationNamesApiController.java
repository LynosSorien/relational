package com.djorquab.relational.relational.web.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.djorquab.relational.relational.bo.RelationNameBO;
import com.djorquab.relational.relational.commons.NamedDTO;
import com.djorquab.relational.relational.services.RelationNameService;

@RestController
@RequestMapping("/api/relation/names")
public class RelationNamesApiController {
	@Autowired
	private RelationNameService service;
	
	@PostMapping
	public ResponseEntity<String> create(@RequestBody NamedDTO relationName) {
		relationName.setName(relationName.getName().trim());
		if (!service.existsRelationWithName(relationName.getName())) {
			service.save(RelationNameBO.builder().name(relationName.getName()).build());
			return ResponseEntity.ok("The relation name has been registered correctly");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("This relation already exists!");
		}
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<RelationNameBO>> relationsList() {
		return ResponseEntity.ok(service.findAll());
	}
}
