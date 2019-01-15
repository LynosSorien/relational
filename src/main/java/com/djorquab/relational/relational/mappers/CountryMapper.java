package com.djorquab.relational.relational.mappers;

import org.mapstruct.Mapper;

import com.djorquab.relational.relational.bo.CountryBO;
import com.djorquab.relational.relational.model.CountryEntity;

@Mapper
public interface CountryMapper extends AbstractMapper<CountryEntity, CountryBO> {

}
