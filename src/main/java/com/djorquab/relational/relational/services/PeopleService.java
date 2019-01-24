package com.djorquab.relational.relational.services;

import com.djorquab.relational.relational.bo.PersonBO;
import com.djorquab.relational.relational.mappers.PersonMapper;
import com.djorquab.relational.relational.model.PersonEntity;
import com.djorquab.relational.relational.repositories.PeopleRepository;

public interface PeopleService extends AbstractEntityService<PersonEntity, Long, PersonBO, PersonMapper, PeopleRepository>{

}
