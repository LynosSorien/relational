package com.djorquab.relational.relational.services;

import com.djorquab.relational.relational.commons.JMSOperation;
import com.djorquab.relational.relational.commons.StorageType;
import com.djorquab.relational.relational.commons.StoreAction;

import java.io.Serializable;

public interface StoreService<E> extends Serializable {
    boolean doAction(StoreAction<E> action);
    JMSOperation getAcceptedOperation();
    StorageType getAcceptedType();
    boolean canExecute(StoreAction<E> action);
}
