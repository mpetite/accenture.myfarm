package com.spring.accenture.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.accenture.entities.Farm;

@Service
public class FarmService {

	@Autowired
	private ChickenService ckService;
	
	@Autowired
	private StatusService sService;

	
	public Farm getFarmByID(long farmID) {
		
		Farm myFarm = new Farm();
		
		myFarm.setChickenList(ckService.findAllChickens(farmID));
		myFarm.setStatus(sService.getStatus(farmID));
		
		return myFarm;
	}

	
}
