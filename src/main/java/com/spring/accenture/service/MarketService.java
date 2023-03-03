package com.spring.accenture.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.accenture.entities.Chicken;
import com.spring.accenture.entities.Farm;
import com.spring.accenture.entities.Farmer;
import com.spring.accenture.exceptions.InsufficientFundsException;
import com.spring.accenture.exceptions.InsufficientLivestockException;
import com.spring.accenture.exceptions.InsufficientStorageException;

@Service
public class MarketService {
//Este servicio maneja la ecena del mercado
	
	//Declaramos constantes
	public static final double CHICKENPRICE = 35;
	public static final double EGGPRICE = 1;

	@Autowired
	private ChickenService chickenService;

	@Autowired
	private FarmService farmService;
	
	@Autowired
	private FarmerService farmerService;

	
	//armo los metodos necesarios
	
	//Manejo el mercado de las gallinas
	public void sellChicken(int amount, long farmID)
			throws InsufficientLivestockException {

		// Consigo el tamaño de la granja
		Farm myFarm = farmService.getFarmByID(farmID);
		Farmer myFarmer = farmerService.getFarmerByID(myFarm.getFarmerID());

		// fijo las ganancias totales
		double totalWinning = CHICKENPRICE * amount;
		
		if (myFarm.getChickenList(1).size()>0)
		{
		// entonces:
		
			// borro la cantidad de gallinas
			for (int byeChicken = 1; byeChicken <= amount; byeChicken++) {
				chickenService.deleteChicken(farmID);
			}
			// """"transfiero"""" el dinero
			myFarmer.setWallet(myFarmer.getWallet() + totalWinning);
			farmerService.saveFarmer(myFarmer);
		}

	else {
		throw new InsufficientLivestockException("No chicken to sell.");
	}
}

	public void buyChicken(int amount, long farmID)
			throws InsufficientFundsException, InsufficientStorageException {

		// Consigo la cantidad de gallinas en la granja y el tamaño de la misma
		Farm myFarm = farmService.getFarmByID(farmID);
		Farmer myFarmer = farmerService.getFarmerByID(myFarm.getFarmerID());
		int chickenCount = myFarm.getChickenList(1).size();

		// fijo el precio total
		double totalPrice = CHICKENPRICE * amount;
		// si:
		if (
		// el conteo de gallinas + la cantidad a comprar no excede el tamaño de la
		// granja
		(myFarm.getSize().equalsIgnoreCase("Medium") && chickenCount < 50
				|| myFarm.getSize().equalsIgnoreCase("Small") && chickenCount < 24
				|| myFarm.getSize().equalsIgnoreCase("Large") && chickenCount < 100)) {
			// y si el usuario tiene suficeinte dinero
			if (myFarmer.getWallet() - totalPrice >= 0)
			// entonces:
			{
				// creo la cantidad de gallinas
				for (int newChicken = 1; newChicken <= amount; newChicken++) {
					Chicken chicken = new Chicken(farmID, 11);
					chickenService.saveChicken(chicken);
				}
				// """"transfiero"""" el dinero
				myFarmer.setWallet(myFarmer.getWallet() - totalPrice);
				farmerService.saveFarmer(myFarmer);
			} else {
				throw new InsufficientFundsException("You fool! You´re 30 cents away from having a quarter!");
			}
		} else {
			throw new InsufficientStorageException("You´re gonna need a bigger boat.");
		}
	}

	//Manejo el mercado de los huevos
	public void sellEgg(int amount, long farmID) 
			throws InsufficientLivestockException {
		// Consigo status de granja
		Farm myFarm = farmService.getFarmByID(farmID);
		Farmer myFarmer = farmerService.getFarmerByID(myFarm.getFarmerID());

		// fijo las ganancias totales
		double totalWinning = EGGPRICE * amount;
		
		if (myFarm.getChickenList(2).size()>0)
		{
				// borro la cantidad de gallinas
				for (int byeChicken = 1; byeChicken <= amount; byeChicken++) {
					chickenService.deleteEgg(farmID);
	
				}
				// """"transfiero"""" el dinero
				myFarmer.setWallet(myFarmer.getWallet() + totalWinning);
				farmerService.saveFarmer(myFarmer);
		}
		else {
			throw new InsufficientLivestockException("No eggs to sell.");
		}
}

	public void buyEgg(int amount, long farmID) 
			throws InsufficientFundsException, InsufficientStorageException {

		// Consigo la cantidad de gallinas en la granja y el tamaño de la misma
		Farm myFarm = farmService.getFarmByID(farmID);
		Farmer myFarmer = farmerService.getFarmerByID(myFarm.getFarmerID());
		int eggCount = myFarm.getChickenList(2).size();

		// fijo el precio total
		double totalPrice = EGGPRICE * amount;
		// si:
		if (
		// el conteo de gallinas + la cantidad a comprar no excede el tamaño de la
		// granja
				(myFarm.getSize().equalsIgnoreCase("Medium") && eggCount < 200 ||
						myFarm.getSize().equalsIgnoreCase("Small") && eggCount < 100 ||
						myFarm.getSize().equalsIgnoreCase("Large") && eggCount < 300)) {
			// y si el usuario tiene suficeinte dinero
			if (myFarmer.getWallet() - totalPrice >= 0)
			// entonces:
			{
				// creo la cantidad de gallinas
				for (int newEgg = 1; newEgg <= amount; newEgg++) {
					Chicken egg = new Chicken(farmID, 0);
					chickenService.saveChicken(egg);
				}
				// """"transfiero"""" el dinero
				myFarmer.setWallet(myFarmer.getWallet() - totalPrice);
				farmerService.saveFarmer(myFarmer);
			} else {
					throw new InsufficientFundsException("You fool! You´re 30 cents away from having a quarter!");
			}
		} else {
				throw new InsufficientStorageException("You´re gonna need a bigger boat.");
		}
	}
	
	//Misc
	public void agregarDia() {
		
		List<Chicken> chickenList = chickenService.findAllLivestock();
		
		List<Chicken> newEggList = new ArrayList<Chicken>();
		
		for (Chicken chicken : chickenList) {
			
			chicken.setAgeDays(chicken.getAgeDays() +1);
			
			chicken.setIsEgg();
			
			newEggList.add(chicken);
			
			if(chicken.getAgeDays()%7==0 && !chicken.getIsEgg()) {
				newEggList.add(new Chicken(chicken.getFarmId(), 0));
			}
		}
		chickenService.saveAllLivestock(newEggList);
	}
}
