package com.djorquab.relational.relational.mappers;

import org.mapstruct.Mapper;

import com.djorquab.relational.relational.dtos.RelationNameDTO;
import com.djorquab.relational.relational.model.RelationName;

@Mapper
public interface RelationNamesMapper extends AbstractMapper<RelationName, RelationNameDTO>{

}
