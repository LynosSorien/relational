package com.djorquab.relational.relational.services.impl;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import lombok.Getter;

import com.djorquab.relational.relational.commons.PagedResult;
import com.djorquab.relational.relational.mappers.AbstractMapper;
import com.djorquab.relational.relational.model.AbstractEntity;
import com.djorquab.relational.relational.services.AbstractEntityService;

public abstract class AbstractEntityServiceImpl<E extends AbstractEntity, K extends Serializable, D extends Serializable,
		M extends AbstractMapper<E, D>, R extends PagingAndSortingRepository<E, K>>
	implements AbstractEntityService<E, K, D, M, R> {
	private static final long serialVersionUID = 747449136676541188L;

	@Getter
	private R repository;
	
	@Getter
	private M mapper;
	
	public AbstractEntityServiceImpl(M mapper, R repository) {
		this.repository = repository;
		this.mapper = mapper;
	}
	
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
	
	@Override
	public PagedResult<D> findAllPaged(int page, int pageSize) {
		Pageable pageable = PageRequest.of(page, pageSize);
		Page<E> pageEntity = repository.findAll(pageable);
		return transform(pageEntity, page, pageSize);
	}

	@Override
	public long count() {
		return getRepository().count();
	}

	private PagedResult<D> transform(Page<E> page, int currentPage, int pageSize) {
		PagedResult<D> result = new PagedResult<>();
		result.setPage(currentPage);
		result.setPageSize(pageSize);
		if (page != null) {
			result.setNumberOfPages(page.getTotalPages());
			result.setTotalElements(page.getTotalElements());
			result.setElements(mapper.entitiesToDtos(page.getContent()));
		}
		return result;
	}
}
