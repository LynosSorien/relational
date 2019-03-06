package com.djorquab.relational.relational.services;

import com.djorquab.relational.relational.bo.ErrorJMSBO;
import com.djorquab.relational.relational.commons.JMSOperation;
import com.djorquab.relational.relational.commons.StorageType;
import com.djorquab.relational.relational.mappers.ErrorJMSMapper;
import com.djorquab.relational.relational.model.ErrorJMS;
import com.djorquab.relational.relational.repositories.ErrorJMSRepository;

public interface ErrorJMSService extends AbstractEntityService<ErrorJMS, Long, ErrorJMSBO, ErrorJMSMapper, ErrorJMSRepository> {
    ErrorJMSBO createError(JMSOperation operation, StorageType system, String payload);
    void tryAgain(Long errorJmsId);
}
