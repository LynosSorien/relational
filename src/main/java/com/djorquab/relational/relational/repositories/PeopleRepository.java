package com.djorquab.relational.relational.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.djorquab.relational.relational.model.PersonEntity;

public interface PeopleRepository extends PagingAndSortingRepository<PersonEntity, Long> {

}
