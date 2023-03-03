package com.spring.accenture.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.accenture.entities.Farmer;

@Repository
public interface FarmerRepository extends CrudRepository<Farmer, Long> {

	public Farmer findById(long farmerID);
}