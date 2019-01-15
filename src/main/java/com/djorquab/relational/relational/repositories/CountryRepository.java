package com.djorquab.relational.relational.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.djorquab.relational.relational.model.CountryEntity;

public interface CountryRepository extends PagingAndSortingRepository<CountryEntity, String> {

}
