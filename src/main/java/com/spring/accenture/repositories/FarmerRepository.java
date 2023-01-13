package com.spring.accenture.repositories;



import org.springframework.data.repository.CrudRepository;

import com.spring.accenture.entities.Farm;
import com.spring.accenture.entities.Farmer;

public interface FarmerRepository extends CrudRepository<Farmer, Long> {


	public Farmer findById(long farmerID);
}