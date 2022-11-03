package com.spring.accenture.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.spring.accenture.entities.Farm;
import com.spring.accenture.service.FarmService;

@RestController("/api")
public class DataReportController {

	
	
	
	@Autowired
	private FarmService farmService;
	
	 //mapeo la granja, donde el usuario va a poder ver el status de su producto, 
    //HTML:farm
    @GetMapping("/farm/{farmID}")
    public Farm farm(@PathVariable long farmID) {

    	return farmService.getFarmByID(farmID);
    }
	
	
}
