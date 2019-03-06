package com.djorquab.relational.relational.services.impl;

import com.djorquab.relational.relational.commons.PagedResult;
import com.djorquab.relational.relational.services.impl.AbstractEntityServiceImpl;
import com.djorquab.relational.relational.utils.Utils;
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

	@Override
	public PagedResult<PersonBO> findPaged(String name, String surname, int page, int size) {
		if (Utils.checkIfStringIsNull(surname)) {
			return findPaged(name, page, size);
		}
		if (Utils.checkIfStringIsNull(name)) {
			return findPagedSurname(surname, page, size);
		}
		return transform(getRepository().findByNameLikeAndSurnameLike(Utils.toLikeFilter(name), Utils.toLikeFilter(surname), createPaging(page, size)), page, size);
	}

	@Override
	public PagedResult<PersonBO> findPaged(String name, int page, int size) {
		if (Utils.checkIfStringIsNull(name)) {
			return findAllPaged(page, size);
		}
		name = Utils.toLikeFilter(name);
		return transform(getRepository().findByNameLike(name, createPaging(page, size)), page, size);
	}

	@Override
	public PagedResult<PersonBO> findPagedSurname(String surname, int page, int size) {
		if (Utils.checkIfStringIsNull(surname)) {
			return findAllPaged(page, size);
		}
		surname = Utils.toLikeFilter(surname);
		return transform(getRepository().findBySurnameLike(surname, createPaging(page, size)), page, size);
	}
}
