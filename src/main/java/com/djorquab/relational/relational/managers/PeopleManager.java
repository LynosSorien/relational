package com.djorquab.relational.relational.managers;

import com.djorquab.relational.relational.bo.PersonBO;
import com.djorquab.relational.relational.services.PeopleService;
import com.djorquab.relational.relational.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PeopleManager {
    @Autowired
    private PeopleService peopleService;

    @Autowired
    private StoreService storeService;

    public PersonBO create(PersonBO person) {
        PersonBO savedEntity = peopleService.save(person);
        storeService.createStoreForPerson(savedEntity);
        return savedEntity;
    }

    public void delete(PersonBO person) {
        if (person != null && person.getId() != null) {
            peopleService.delete(person.getId());
            storeService.deleteStoreForPerson(person);
        }
    }

    public void delete(Long id) {
        delete(peopleService.getById(id));
    }
}
