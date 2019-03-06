package com.djorquab.relational.relational.managers;

import com.djorquab.relational.relational.bo.PersonBO;
import com.djorquab.relational.relational.commons.JMSOperation;
import com.djorquab.relational.relational.commons.StorageType;
import com.djorquab.relational.relational.commons.StoreAction;
import com.djorquab.relational.relational.services.PeopleService;
import com.djorquab.relational.relational.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PeopleManager {
    @Autowired
    private PeopleService peopleService;

    @Autowired
    private List<StoreService> services;

    public PersonBO create(PersonBO person) {
        PersonBO savedEntity = peopleService.save(person);
        executeAction(savedEntity, JMSOperation.CREATE_FOLDER);
        return savedEntity;
    }

    public void delete(PersonBO person) {
        if (person != null && person.getId() != null) {
            peopleService.delete(person.getId());
            executeAction(person, JMSOperation.DELETE_FOLDER);
        }
    }

    private void executeAction(final PersonBO person, final JMSOperation operation) {
        services.forEach(service -> service.doAction(StoreAction.builder().operation(operation).type(StorageType.PEOPLE).payload(person.getId()).build()));
    }

    public void delete(Long id) {
        delete(peopleService.getById(id));
    }

    public void deleteAll() {
        List<PersonBO> people = peopleService.findAll();
        for (PersonBO person : people) {
            delete(person);
        }
    }
}
