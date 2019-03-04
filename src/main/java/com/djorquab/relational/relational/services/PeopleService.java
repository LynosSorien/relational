package com.djorquab.relational.relational.services;

import com.djorquab.relational.relational.bo.PersonBO;
import com.djorquab.relational.relational.commons.PagedResult;
import com.djorquab.relational.relational.mappers.PersonMapper;
import com.djorquab.relational.relational.model.PersonEntity;
import com.djorquab.relational.relational.repositories.PeopleRepository;

public interface PeopleService extends AbstractEntityService<PersonEntity, Long, PersonBO, PersonMapper, PeopleRepository>{
    PagedResult<PersonBO> findPaged(String name, String surname, int page, int size);
    PagedResult<PersonBO> findPaged(String name, int page, int size);
    PagedResult<PersonBO> findPagedSurname(String surname, int page, int size);
}
