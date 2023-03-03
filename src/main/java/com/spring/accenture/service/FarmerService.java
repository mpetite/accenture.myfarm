package com.spring.accenture.service;

import org.springframework.stereotype.Service;

import com.spring.accenture.entities.Farmer;
import com.spring.accenture.repositories.FarmerRepository;

@Service
public class FarmerService {

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
