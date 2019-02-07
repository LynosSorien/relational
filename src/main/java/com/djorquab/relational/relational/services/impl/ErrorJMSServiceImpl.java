package com.djorquab.relational.relational.services.impl;

import com.djorquab.relational.relational.bo.ErrorJMSBO;
import com.djorquab.relational.relational.bo.PersonBO;
import com.djorquab.relational.relational.commons.JMSOperation;
import com.djorquab.relational.relational.commons.JMSSystem;
import com.djorquab.relational.relational.mappers.ErrorJMSMapper;
import com.djorquab.relational.relational.model.ErrorJMS;
import com.djorquab.relational.relational.repositories.ErrorJMSRepository;
import com.djorquab.relational.relational.services.ErrorJMSService;
import com.djorquab.relational.relational.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service("errorJMSService")
public class ErrorJMSServiceImpl extends AbstractEntityServiceImpl<ErrorJMS, Long, ErrorJMSBO, ErrorJMSMapper, ErrorJMSRepository>
    implements ErrorJMSService {

    @Autowired
    private StoreService storeService;

    public ErrorJMSServiceImpl(@Autowired ErrorJMSMapper mapper, @Autowired ErrorJMSRepository repository) {
        super(mapper, repository);
    }

    @Override
    public ErrorJMSBO createError(JMSOperation operation, JMSSystem system, String payload) {
        return save(ErrorJMSBO.builder().operation(operation).system(system).payload(payload)
                .lastModified(Calendar.getInstance().getTime()).createdOn(Calendar.getInstance().getTime()).build());
    }

    @Override
    public void tryAgain(Long errorJmsId) {
        ErrorJMSBO error = getById(errorJmsId);
        boolean canBeTryied;
        if (error != null && error.getId() != null) {
            switch(error.getSystem()) {
                case PEOPLE:
                    canBeTryied = tryAgainPeopleSystem(error);
                    break;
                default:
                    canBeTryied = false;
                    break;
            }
            if (canBeTryied) {
                delete(errorJmsId);
            }
        }
    }

    private boolean tryAgainPeopleSystem(ErrorJMSBO error) {
        PersonBO person = PersonBO.builder().id(Long.parseLong(error.getPayload())).build();
        switch(error.getOperation()) {
            case CREATE_FOLDER:
                storeService.createStoreForPerson(person);
                return true;
            case DELETE_FOLDER:
                storeService.deleteStoreForPerson(person);
                return true;
            default:break;
        }
        return false;
    }
}
