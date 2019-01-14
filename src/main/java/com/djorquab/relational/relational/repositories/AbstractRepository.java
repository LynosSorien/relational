package com.djorquab.relational.relational.repositories;

import java.io.Serializable;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.djorquab.relational.relational.model.AbstractEntity;

public interface AbstractRepository<E extends AbstractEntity, EK extends Serializable> extends PagingAndSortingRepository<E, EK> {
	
}
