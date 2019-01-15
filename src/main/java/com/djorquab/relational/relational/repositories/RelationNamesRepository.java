package com.djorquab.relational.relational.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.djorquab.relational.relational.model.RelationName;

public interface RelationNamesRepository extends PagingAndSortingRepository<RelationName, Long>{
	boolean existsByName(String name);
}
