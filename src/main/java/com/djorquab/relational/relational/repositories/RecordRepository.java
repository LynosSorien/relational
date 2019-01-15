package com.djorquab.relational.relational.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.djorquab.relational.relational.model.RecordEntity;

public interface RecordRepository extends PagingAndSortingRepository<RecordEntity, Long> {

}
