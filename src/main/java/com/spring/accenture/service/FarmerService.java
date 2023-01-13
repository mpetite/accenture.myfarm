package com.spring.accenture.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.spring.accenture.entities.Farmer;
import com.spring.accenture.repositories.FarmerRepository;

public class FarmerService {
	
	@Autowired
	private FarmerRepository farmerRepository;
	
	public Farmer getFarmerByID(long farmerID) {
		
		Farmer myFarm = new Farmer();
		
		Farmer repoReturn = farmerRepository.findById(farmerID);
		
		myFarm = repoReturn;
		
		return myFarm;
	}	
	
	public void saveFarmer(Farmer farmer) {
		farmerRepository.save(farmer);
	}

}
