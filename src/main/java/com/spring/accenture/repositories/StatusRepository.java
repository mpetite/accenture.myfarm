package com.spring.accenture.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.accenture.entities.Status;
@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {

	
	
	
}
