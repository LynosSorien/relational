package com.djorquab.relational.relational.services.storage;

import com.djorquab.relational.relational.bo.PersonBO;
import com.djorquab.relational.relational.commons.StorageType;
import com.djorquab.relational.relational.commons.StoreAction;
import com.djorquab.relational.relational.services.AbstractStoreService;
import com.djorquab.relational.relational.services.PeopleService;
import lombok.Getter;
import org.springframework.jms.core.JmsTemplate;

public abstract class AbstractPeopleStorageService extends AbstractStoreService<Long, PersonBO> {
    @Getter
    private JmsTemplate jmsTempalte;

    private PeopleService service;

    public AbstractPeopleStorageService(JmsTemplate jmsTemplate, PeopleService service) {
        this.jmsTempalte = jmsTemplate;
        this.service = service;
    }

    @Override
    public StorageType getAcceptedType() {
        return StorageType.PEOPLE;
    }

    @Override
    public boolean doAction(StoreAction<Long> action) {
        if (!canExecute(action)) {
            return false;
        }
        //PersonBO person = service.getById(action.getPayload());
        PersonBO person = PersonBO.builder().id(action.getPayload()).build();
        if (person != null) {
            execute(person);
        }
        return true;
    }
}
