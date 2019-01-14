package com.djorquab.relational.relational.services;

import java.io.Serializable;
import java.util.List;

import com.djorquab.relational.relational.mappers.AbstractMapper;
import com.djorquab.relational.relational.model.AbstractEntity;
import com.djorquab.relational.relational.repositories.AbstractRepository;

public interface AbstractEntityService<E extends AbstractEntity, K extends Serializable, BO extends Serializable, 
	M extends AbstractMapper<E, BO>,
	R extends AbstractRepository<E, K>> extends Serializable {
	
	BO getById(K id);
	BO save(BO bo);
	void delete(K id);
	
	List<BO> findAll();
}
