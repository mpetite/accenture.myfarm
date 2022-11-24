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
		
		myFarm.setChickenList(ckService.findProducts(farmID, 0));
		myFarm.setStatus(sService.getStatus(farmID));
		
		return myFarm;
	}
	

	
}
