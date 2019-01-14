package com.djorquab.relational.relational.mappers;

import java.io.Serializable;
import java.util.List;

import com.djorquab.relational.relational.model.AbstractEntity;

public interface AbstractMapper<E extends AbstractEntity, D extends Serializable> {
	E cloneEntity(E entity);
	D cloneDTO(D dto);
	D entityToDto(E entity);
	E dtoToEntity(D dto);
	List<E> dtosToEntities(Iterable<D> dtos);
	List<D> entitiesToDtos(Iterable<E> entities);
}
