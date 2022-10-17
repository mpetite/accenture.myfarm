package com.spring.accenture.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.spring.accenture.entities.Chicken;
import com.spring.accenture.entities.Farm;

public interface ChickenRepository extends CrudRepository<Chicken, Long> {

	
	List<Chicken> findAll();
	

	Optional<Chicken> findById(Long Id);


}
