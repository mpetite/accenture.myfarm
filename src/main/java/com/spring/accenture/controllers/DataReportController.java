package com.spring.accenture.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.accenture.entities.Farm;
import com.spring.accenture.service.FarmService;

@RestController
@RequestMapping("/api")
public class DataReportController {

	@Autowired
	private FarmService farmService;

	// mapeo la granja, donde el usuario va a poder ver el status de su producto,
	// HTML:farm
	@GetMapping("/farm/{farmID}")
	public Farm farm(@PathVariable long farmID) {
		
		Farm theFarm = farmService.getFarmByID(farmID);
		
		String farmLocationName = theFarm.getStatus().getLocationID();
		String farmLocationSize = theFarm.getStatus().getSize();
		double farmLocationCurrency = theFarm.getStatus().getMoney();
		int farmChickenCount = theFarm.getChickenList(1).size();
		int farmEggCount = theFarm.getChickenList(2).size();

		return farmService.getFarmByID(farmID);
	}
	
	
	// mapeo la granja, donde el usuario va a poder ver el status de su producto,
	// HTML:farm
	@PostMapping(value="/buyChicken")
	public String buyChicken(@RequestBody body) {
		
	return "Hello";
	}


}
