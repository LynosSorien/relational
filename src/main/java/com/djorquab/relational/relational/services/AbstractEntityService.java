package com.djorquab.relational.relational.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.djorquab.relational.relational.commons.PagedResult;
import com.djorquab.relational.relational.mappers.AbstractMapper;
import com.djorquab.relational.relational.model.AbstractEntity;

public interface AbstractEntityService<E extends AbstractEntity, K extends Serializable, BO extends Serializable, 
	M extends AbstractMapper<E, BO>,
	R extends PagingAndSortingRepository<E, K>> extends Serializable {
	
	BO getById(K id);
	BO save(BO bo);
	void delete(K id);
	
	List<BO> findAll();
	
	PagedResult<BO> findAllPaged(int page, int pageSize);
}
