package com.djorquab.relational.relational.web.api;

import com.djorquab.relational.relational.bo.PersonBO;
import com.djorquab.relational.relational.commons.PagedResult;
import com.djorquab.relational.relational.commons.ResponseDTO;
import com.djorquab.relational.relational.managers.PeopleManager;
import com.djorquab.relational.relational.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/api/people")
public class PeopleApiController {
    @Autowired
    private PeopleManager manager;

    @Autowired
    private PeopleService service;

    @PostMapping
    public ResponseEntity<ResponseDTO<Serializable>> create(@RequestBody PersonBO person) {
        try {
            manager.create(person);
            return ResponseEntity.ok(ResponseDTO.builder().message("Entity added").build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseDTO.builder().message("Unexpected error while trying to create this entity: "+e.getLocalizedMessage()).payload(e).build());
        }
    }

    @GetMapping
    public ResponseEntity<PagedResult<PersonBO>> search(@RequestParam(name = "name", required = false) String name,
                                                        @RequestParam(name = "surname", required = false) String surname,
                                                        @RequestParam(name = "page", defaultValue = "1") int page,
                                                        @RequestParam(name = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(service.findPaged(name, surname, page, size));
    }
}
