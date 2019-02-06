package com.djorquab.relational.relational.services;

import com.djorquab.relational.relational.bo.RelationNameBO;
import com.djorquab.relational.relational.mappers.RelationNamesMapper;
import com.djorquab.relational.relational.model.RelationName;
import com.djorquab.relational.relational.repositories.RelationNamesRepository;

public interface RelationNameService extends AbstractEntityService<RelationName, Long, RelationNameBO, RelationNamesMapper, RelationNamesRepository> {
	boolean existsRelationWithName(String name);
	RelationNameBO getByName(String name);
}
