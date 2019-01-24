package com.djorquab.relational.relational.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.djorquab.relational.relational.bo.PersonBO;
import com.djorquab.relational.relational.mappers.PersonMapper;
import com.djorquab.relational.relational.model.PersonEntity;
import com.djorquab.relational.relational.repositories.PeopleRepository;
import com.djorquab.relational.relational.services.PeopleService;

@Service("peopleService")
public class PeopleServiceImpl extends AbstractEntityServiceImpl<PersonEntity, Long, PersonBO, PersonMapper, PeopleRepository> 
	implements PeopleService {
	
	private static final long serialVersionUID = -5626947371108111383L;

	public PeopleServiceImpl(@Autowired PersonMapper mapper, @Autowired PeopleRepository repository) {
		super(mapper, repository);
	}
}
