package com.djorquab.relational.relational.services.impl;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import lombok.Getter;

import com.djorquab.relational.relational.mappers.AbstractMapper;
import com.djorquab.relational.relational.model.AbstractEntity;
import com.djorquab.relational.relational.repositories.AbstractRepository;
import com.djorquab.relational.relational.services.AbstractEntityService;

public abstract class AbstractEntityServiceImpl<E extends AbstractEntity, K extends Serializable, D extends Serializable,
		M extends AbstractMapper<E, D>, R extends AbstractRepository<E, K>>
	implements AbstractEntityService<E, K, D, M, R> {
	private static final long serialVersionUID = 747449136676541188L;

	@Getter
	private R repository;
	
	@Getter
	private M mapper;
	
	protected E getEntity(K id) {
		Optional<E> optional = repository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	@Override
	public D getById(K id) {
		return mapper.entityToDto(getEntity(id));
	}

	@Override
	public D save(D bo) {
		E entity = mapper.dtoToEntity(bo);
		if (entity.getCreatedOn() == null) {
			entity.setCreatedOn(Calendar.getInstance().getTime());
		}
		entity.setLastModified(Calendar.getInstance().getTime());
		return mapper.entityToDto(repository.save(entity));
	}

	@Override
	public void delete(K id) {
		E entity = getEntity(id);
		if (entity != null) {
			repository.delete(entity);
		}
		
	}

	@Override
	public List<D> findAll() {
		return mapper.entitiesToDtos(repository.findAll());
	}
}
