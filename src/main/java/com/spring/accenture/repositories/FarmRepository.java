package com.spring.accenture.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.spring.accenture.entities.Farm;


@Repository
public interface FarmRepository extends CrudRepository<Farm, Long> {

	public Farm findById(long farmID);
	
}
