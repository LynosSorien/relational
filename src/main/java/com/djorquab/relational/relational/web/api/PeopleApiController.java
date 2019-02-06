package com.djorquab.relational.relational.web.api;

import com.djorquab.relational.relational.bo.PersonBO;
import com.djorquab.relational.relational.commons.ResponseDTO;
import com.djorquab.relational.relational.managers.PeopleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@RestController
@RequestMapping("/api/people")
public class PeopleApiController {
    @Autowired
    private PeopleManager manager;

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
}
