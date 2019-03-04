package com.djorquab.relational.relational.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.djorquab.relational.relational.model.PersonEntity;

public interface PeopleRepository extends PagingAndSortingRepository<PersonEntity, Long> {
    Page<PersonEntity> findByNameLike(String name, Pageable pageable);
    Page<PersonEntity> findBySurnameLike(String surname, Pageable pageable);
    Page<PersonEntity> findByNameLikeAndSurnameLike(String name, String surname, Pageable pageable);
}
