package com.djorquab.relational.relational.mappers;

import org.mapstruct.Mapper;

import com.djorquab.relational.relational.bo.PeopleRelationBO;
import com.djorquab.relational.relational.model.PeopleRelationEntity;

@Mapper(uses = {PersonMapper.class, RelationNamesMapper.class})
public interface PeopleRelationMapper extends AbstractMapper<PeopleRelationEntity, PeopleRelationBO>{

}
