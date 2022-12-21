package com.spring.accenture.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.accenture.entities.Status;
@Repository
public interface StatusRepository extends CrudRepository<Status, Long> {

	
	
	
}
