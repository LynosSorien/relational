package com.djorquab.relational.relational.services.impl;

import com.djorquab.relational.relational.bo.ErrorJMSBO;
import com.djorquab.relational.relational.bo.PersonBO;
import com.djorquab.relational.relational.commons.JMSOperation;
import com.djorquab.relational.relational.commons.StorageType;
import com.djorquab.relational.relational.commons.StoreAction;
import com.djorquab.relational.relational.mappers.ErrorJMSMapper;
import com.djorquab.relational.relational.model.ErrorJMS;
import com.djorquab.relational.relational.repositories.ErrorJMSRepository;
import com.djorquab.relational.relational.services.ErrorJMSService;
import com.djorquab.relational.relational.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service("errorJMSService")
public class ErrorJMSServiceImpl extends AbstractEntityServiceImpl<ErrorJMS, Long, ErrorJMSBO, ErrorJMSMapper, ErrorJMSRepository>
    implements ErrorJMSService {

    @Autowired
    private List<StoreService> services;

    public ErrorJMSServiceImpl(@Autowired ErrorJMSMapper mapper, @Autowired ErrorJMSRepository repository) {
        super(mapper, repository);
    }

    @Override
    public ErrorJMSBO createError(JMSOperation operation, StorageType system, String payload) {
        return save(ErrorJMSBO.builder().operation(operation).storageType(system).payload(payload)
                .lastModified(Calendar.getInstance().getTime()).createdOn(Calendar.getInstance().getTime()).build());
    }

    @Override
    public void tryAgain(Long errorJmsId) {
        ErrorJMSBO error = getById(errorJmsId);
        if (error != null && error.getId() != null) {
            StoreAction action = StoreAction.builder().operation(error.getOperation()).type(error.getStorageType()).payload(error.getPayload()).build();
            services.stream().forEach(service -> service.doAction(action));
            delete(error.getId());
        }
    }
}
