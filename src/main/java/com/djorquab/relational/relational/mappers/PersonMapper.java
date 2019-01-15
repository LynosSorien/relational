package com.djorquab.relational.relational.mappers;

import org.mapstruct.Mapper;

import com.djorquab.relational.relational.bo.PersonBO;
import com.djorquab.relational.relational.model.PersonEntity;

@Mapper(uses = {PeopleRelationMapper.class})
public interface PersonMapper extends AbstractMapper<PersonEntity, PersonBO> {
	
}
