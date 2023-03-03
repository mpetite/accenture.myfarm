package com.spring.accenture.service;

import org.springframework.stereotype.Service;

import com.spring.accenture.entities.Farm;
import com.spring.accenture.repositories.FarmRepository;

@Service
public class FarmService {
//servicio que se encarga de la granja en total, tanto chicken como localidad en si

	private FarmRepository farmRepository;

	// Metodos
	public Farm getFarmByID(long farmID) {

		Farm myFarm = new Farm();

		Farm repoReturn = farmRepository.findById(farmID);

		myFarm = repoReturn;

		return myFarm;
	}

	public void saveFarm(Farm farm) {
		farmRepository.save(farm);
	}
}
