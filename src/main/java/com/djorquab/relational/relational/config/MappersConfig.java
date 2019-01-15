package com.djorquab.relational.relational.config;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.djorquab.relational.relational.mappers.CountryMapper;
import com.djorquab.relational.relational.mappers.PeopleRelationMapper;
import com.djorquab.relational.relational.mappers.PersonMapper;
import com.djorquab.relational.relational.mappers.RecordMapper;
import com.djorquab.relational.relational.mappers.RelationNamesMapper;
import com.djorquab.relational.relational.mappers.RolesMapper;

@Configuration
public class MappersConfig {

	@Bean
	public RelationNamesMapper relationNamesMapper() {
		return Mappers.getMapper(RelationNamesMapper.class);
	}
	
	@Bean
	public RolesMapper rolesMapper() {
		return Mappers.getMapper(RolesMapper.class);
	}
	
	@Bean
	public CountryMapper countryMapper() {
		return Mappers.getMapper(CountryMapper.class);
	}
	
	@Bean
	public RecordMapper recordMapper() {
		return Mappers.getMapper(RecordMapper.class);
	}
	
	@Bean
	public PersonMapper personMapper() {
		return Mappers.getMapper(PersonMapper.class);
	}
	
	@Bean
	public PeopleRelationMapper peopleRelationMapper() {
		return Mappers.getMapper(PeopleRelationMapper.class);
	}
}
