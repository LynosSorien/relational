package com.djorquab.relational.relational.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.djorquab.relational.relational.model.AuthenticatedUser;

public interface AuthenticatedUsersRepository extends PagingAndSortingRepository<AuthenticatedUser, Long>{
	boolean existsByEmail(String email);
	AuthenticatedUser findByEmail(String email);
}
