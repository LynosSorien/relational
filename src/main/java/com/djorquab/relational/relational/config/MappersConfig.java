package com.djorquab.relational.relational.config;

import com.djorquab.relational.relational.mappers.*;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

	@Bean
	public ErrorJMSMapper errorJMSMapper() {
		return Mappers.getMapper(ErrorJMSMapper.class);
	}
}
