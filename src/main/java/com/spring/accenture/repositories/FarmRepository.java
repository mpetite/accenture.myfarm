package com.spring.accenture.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.spring.accenture.entities.Farm;

public interface FarmRepository extends CrudRepository<Farm, Long> {

	
	List<Farm> findAll();
	

	Optional<Farm> findById(Long id);
}
