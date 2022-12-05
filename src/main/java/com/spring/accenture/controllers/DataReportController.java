package com.spring.accenture.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.accenture.entities.Farm;
import com.spring.accenture.exceptions.InsufficientFundsException;
import com.spring.accenture.exceptions.InsufficientLivestockException;
import com.spring.accenture.exceptions.InsufficientStorageException;
import com.spring.accenture.service.FarmService;
import com.spring.accenture.service.MarketService;

@RestController
@RequestMapping("/api")
public class DataReportController {

	@Autowired
	private FarmService farmService;
	
	@Autowired
	private MarketService marketService;

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

	@PostMapping(value="/buyChicken")
	public ResponseEntity buyChicken(@RequestParam String id) {
		
		try {
			marketService.buyChicken(1, Long.parseLong(id));
			return ResponseEntity.ok("Comprado!");
		} catch (NumberFormatException | InsufficientFundsException | InsufficientStorageException e) {
			System.err.println(e.getMessage());
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}
	
	@PostMapping(value="/buyEgg")
	public ResponseEntity buyEgg(@RequestParam String id) {
		
		try {
			marketService.buyEgg(1, Long.parseLong(id));
			return ResponseEntity.ok("Comprado!");
		} catch (NumberFormatException | InsufficientFundsException | InsufficientStorageException e) {
			System.err.println(e.getMessage());
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}
	
	@PostMapping(value="/sellChicken")
	public ResponseEntity sellChicken(@RequestParam String id) {
		
		try {
			marketService.sellChicken(1, Long.parseLong(id));
			return ResponseEntity.ok("Vendido!");
		} catch (NumberFormatException | InsufficientLivestockException e) {
			System.err.println(e.getMessage());
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}
	
	@PostMapping(value="/sellEgg")
	public ResponseEntity sellEgg(@RequestParam String id) {
		
		try {
			marketService.sellEgg(1, Long.parseLong(id));
			return ResponseEntity.ok("Vendido!");
		} catch (NumberFormatException | InsufficientLivestockException e) {
			System.err.println(e.getMessage());
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}
}
