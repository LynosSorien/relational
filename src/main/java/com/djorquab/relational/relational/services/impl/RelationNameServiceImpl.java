package com.djorquab.relational.relational.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.djorquab.relational.relational.bo.RelationNameBO;
import com.djorquab.relational.relational.mappers.RelationNamesMapper;
import com.djorquab.relational.relational.model.RelationName;
import com.djorquab.relational.relational.repositories.RelationNamesRepository;
import com.djorquab.relational.relational.services.RelationNameService;

@Service("relationNameService")
public class RelationNameServiceImpl
	extends AbstractEntityServiceImpl<RelationName, Long, RelationNameBO, RelationNamesMapper, RelationNamesRepository>
	implements RelationNameService {
	private static final long serialVersionUID = -4755358665444657976L;

	public RelationNameServiceImpl(@Autowired RelationNamesMapper mapper, @Autowired RelationNamesRepository repository) {
		super(mapper, repository);
	}

	@Override
	public RelationNameBO getByName(String name) {
		return getMapper().entityToDto(getRepository().findByName(name));
	}

	@Override
	public boolean existsRelationWithName(String name) {
		return getRepository().existsByName(name);
	}
}
