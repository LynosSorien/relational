package com.djorquab.relational.relational.services;

import com.djorquab.relational.relational.bo.PersonBO;

import java.io.Serializable;

public interface StoreService extends Serializable {
    void createStoreForPerson(PersonBO person);
    void deleteStoreForPerson(PersonBO person);
}
