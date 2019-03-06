package com.djorquab.relational.relational.services;

import com.djorquab.relational.relational.commons.StoreAction;

public abstract class AbstractStoreService<E, T> implements StoreService<E> {
    @Override
    public boolean canExecute(StoreAction<E> action) {
        return action != null && getAcceptedOperation().equals(action.getOperation()) && getAcceptedType().equals(action.getType());
    }

    protected abstract void execute(T t);
}
