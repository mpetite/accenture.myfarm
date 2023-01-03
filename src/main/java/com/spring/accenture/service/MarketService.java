package com.spring.accenture.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.accenture.entities.Chicken;
import com.spring.accenture.entities.FarmSizeEnums;
import com.spring.accenture.entities.Status;
import com.spring.accenture.exceptions.InsufficientFundsException;
import com.spring.accenture.exceptions.InsufficientLivestockException;
import com.spring.accenture.exceptions.InsufficientStorageException;

@Service
public class MarketService {
//Este servicio maneja la ecena del mercado

	// Declaramos constantes
	public static final double CHICKENPRICE = 35;
	public static final double EGGPRICE = 1;

	@Autowired
	private ChickenService chickenService;

	@Autowired
	private StatusService statusService;

	// armo los metodos necesarios

	// Manejo el mercado de las gallinas
	public void sellChicken(int amount, long farmID) throws InsufficientLivestockException {

		// Consigo el tamaño de la granja
		Status farmStatus = statusService.getStatus(farmID);

		// fijo las ganancias totales
		double totalWinning = CHICKENPRICE * amount;

		if (chickenService.findProducts(farmID, 1).size() > 0) {
			// entonces:

			// borro la cantidad de gallinas
			for (int byeChicken = 1; byeChicken <= amount; byeChicken++) {
				chickenService.deleteChicken(farmID);
			}
			// """"transfiero"""" el dinero
			farmStatus.setMoney(farmStatus.getMoney() + totalWinning);
			statusService.saveStatus(farmStatus);
		}

		else {
			throw new InsufficientLivestockException("No chicken to sell.");
		}
	}

	public void buyChicken(int amount, long farmID) throws InsufficientFundsException, InsufficientStorageException {

		// Consigo la cantidad de gallinas en la granja y el tamaño de la misma
		int chickenCount = chickenService.findProducts(farmID, 1).size();
		Status farmStatus = statusService.getStatus(farmID);

		// fijo el precio total
		double totalPrice = CHICKENPRICE * amount;
		// si:
		if (
		// el conteo de gallinas + la cantidad a comprar no excede el tamaño de la
		// granja
		(FarmSizeEnums.MEDIUM.name().equalsIgnoreCase(farmStatus.getSize())
				&& chickenCount < FarmSizeEnums.MEDIUM.chickenMax
				|| FarmSizeEnums.SMALL.name().equalsIgnoreCase(farmStatus.getSize())
						&& chickenCount < FarmSizeEnums.SMALL.chickenMax
				|| FarmSizeEnums.LARGE.name().equalsIgnoreCase(farmStatus.getSize())
						&& chickenCount < FarmSizeEnums.LARGE.chickenMax)) {
			// y si el usuario tiene suficeinte dinero
			if (farmStatus.getMoney() - totalPrice >= 0)
			// entonces:
			{
				// creo la cantidad de gallinas
				for (int newChicken = 1; newChicken <= amount; newChicken++) {
					Chicken chicken = new Chicken(farmID, 11);
					chickenService.saveChicken(chicken);
				}
				// """"transfiero"""" el dinero
				statusService.getStatus(farmID).setMoney(farmStatus.getMoney() - totalPrice);
				statusService.saveStatus(farmStatus);
			} else {
				throw new InsufficientFundsException("You fool! You´re 30 cents away from having a quarter!");
			}
		} else {
			throw new InsufficientStorageException("You´re gonna need a bigger boat.");
			// throw new
			// InsufficientStorageException(String.valueOf(FarmSizeEnums.SMALL.name().equalsIgnoreCase(farmStatus.getSize()))
			// );
		}
	}

	// Manejo el mercado de los huevos
	public void sellEgg(int amount, long farmID) throws InsufficientLivestockException {
		// Consigo status de granja
		Status farmStatus = statusService.getStatus(farmID);

		// fijo las ganancias totales
		double totalWinning = EGGPRICE * amount;

		if (chickenService.findProducts(farmID, 2).size() > 0) {
			// borro la cantidad de gallinas
			for (int byeChicken = 1; byeChicken <= amount; byeChicken++) {
				chickenService.deleteEgg(farmID);

			}
			// """"transfiero"""" el dinero
			statusService.getStatus(farmID).setMoney(farmStatus.getMoney() + totalWinning);
			statusService.saveStatus(farmStatus);
		} else {
			throw new InsufficientLivestockException("No eggs to sell.");
		}
	}

	public void buyEgg(int amount, long farmID) throws InsufficientFundsException, InsufficientStorageException {

		// Consigo la cantidad de gallinas en la granja y el tamaño de la misma
		int eggCount = chickenService.findProducts(farmID, 2).size();
		Status farmStatus = statusService.getStatus(farmID);

		// fijo el precio total
		double totalPrice = EGGPRICE * amount;
		// si:
		if (
		// el conteo de gallinas + la cantidad a comprar no excede el tamaño de la
		// granja
		(FarmSizeEnums.MEDIUM.name().equalsIgnoreCase(farmStatus.getSize()) && eggCount < FarmSizeEnums.MEDIUM.eggMax
				|| FarmSizeEnums.SMALL.name().equalsIgnoreCase(farmStatus.getSize())
						&& eggCount < FarmSizeEnums.SMALL.eggMax
				|| FarmSizeEnums.LARGE.name().equalsIgnoreCase(farmStatus.getSize())
						&& eggCount < FarmSizeEnums.LARGE.eggMax)) {
			// y si el usuario tiene suficeinte dinero
			if (farmStatus.getMoney() - totalPrice >= 0)
			// entonces:
			{
				// creo la cantidad de gallinas
				for (int newEgg = 1; newEgg <= amount; newEgg++) {
					Chicken egg = new Chicken(farmID, 0);
					chickenService.saveChicken(egg);
				}
				// """"transfiero"""" el dinero
				statusService.getStatus(farmID).setMoney(farmStatus.getMoney() - totalPrice);
				statusService.saveStatus(farmStatus);
			} else {
				throw new InsufficientFundsException("You fool! You´re 30 cents away from having a quarter!");
			}
		} else {
			throw new InsufficientStorageException("You´re gonna need a bigger boat.");
		}
	}

	// Misc
	public void agregarDia(long farmID) throws InsufficientLivestockException {

		List<Chicken> chickenList = chickenService.findProducts(farmID, 0);

		List<Chicken> newEggList = new ArrayList<Chicken>();
		
		List<Chicken> newChickenList = new ArrayList<Chicken>();
		
		List<Chicken> totalList = new ArrayList<Chicken>();
		
		
		//vendo excedentes de base (stability)
		totalList = excedente(chickenList, farmID);
		
		//agrego dias y separo huevos y gallinas
		for (Chicken chicken : totalList) {

			chicken.setAgeDays(chicken.getAgeDays() + 1);

			chicken.setIsEgg();
			
			if(chicken.getIsEgg()) {
				newEggList.add(chicken);
			}
			else {
				newChickenList.add(chicken);
			}
			
			if (chicken.getAgeDays() % 7 == 0 && !chicken.getIsEgg()) {
				newEggList.add(new Chicken(chicken.getFarmId(), 0));
			}

		}
		
		//vendo excedentes nuevos (if any)
		totalList = excedente(Stream.concat(newChickenList.stream(), newEggList.stream()).collect(Collectors.toList()), farmID);
		
		//guardo lista nueva
		chickenService.replaceAllLivestock(totalList);
	}
	
	private List<Chicken> excedente(List<Chicken> chickenList, long farmID) {
		
		List<Chicken> newEggList = new ArrayList<Chicken>();
		
		List<Chicken> newChickenList = new ArrayList<Chicken>();
		
		Status farmStatus = statusService.getStatus(farmID);

		int chickenCount = chickenList.stream().filter((c -> ! c.getIsEgg()))
				.collect(Collectors.toList()).size();
		int eggCount = chickenList.stream().filter((c -> c.getIsEgg())).collect(Collectors.toList())
				.size();
		
		//separo las gallinas y los huevos
		for(Chicken chicken: chickenList) {
			if (chicken.getIsEgg()) {
				newEggList.add(chicken);
			}
			else {
				newChickenList.add(chicken);
			}
		}
		//elimino excedentes
		// SMALL
				if (FarmSizeEnums.SMALL.name().equalsIgnoreCase(farmStatus.getSize())) {
					if (chickenCount > FarmSizeEnums.SMALL.chickenMax) {
						
						int excedente = chickenCount - FarmSizeEnums.SMALL.chickenMax;
						
						//sellChicken(excedente, farmID);
						
						for (int i = 0; i < excedente; i++) {
							newChickenList.remove(0);
							farmStatus.setMoney(farmStatus.getMoney() + CHICKENPRICE);
						}
						
						
					}
					if (eggCount > FarmSizeEnums.SMALL.eggMax) {
						
						int excedente = eggCount - FarmSizeEnums.SMALL.eggMax;
						
						//sellEgg(excedente, farmID);
						
						for (int i = 0; i < excedente; i++) {
							newEggList.remove(0);
							farmStatus.setMoney(farmStatus.getMoney() + EGGPRICE);
						}				
					}
				}

				// MEDIUM
				if (FarmSizeEnums.MEDIUM.name().equalsIgnoreCase(farmStatus.getSize())) {
					if (chickenCount > FarmSizeEnums.MEDIUM.chickenMax) {
						
						int excedente = chickenCount - FarmSizeEnums.MEDIUM.chickenMax;
						
						//sellChicken(excedente, farmID);
						
						for (int i = 0; i < excedente; i++) {
							newChickenList.remove(0);
							farmStatus.setMoney(farmStatus.getMoney() + CHICKENPRICE);
						}
						
						
					}
					if (eggCount > FarmSizeEnums.MEDIUM.eggMax) {
						
						int excedente = eggCount - FarmSizeEnums.MEDIUM.eggMax;
						
						//sellEgg(excedente, farmID);
						
						for (int i = 0; i < excedente; i++) {
							newEggList.remove(0);
							farmStatus.setMoney(farmStatus.getMoney() + EGGPRICE);
						}				
					}
				}

				// LARGE
				if (FarmSizeEnums.LARGE.name().equalsIgnoreCase(farmStatus.getSize())) {
					if (chickenCount > FarmSizeEnums.LARGE.chickenMax) {
						
						int excedente = chickenCount - FarmSizeEnums.LARGE.chickenMax;
						
						//sellChicken(excedente, farmID);
						
						for (int i = 0; i < excedente; i++) {
							newChickenList.remove(0);
							farmStatus.setMoney(farmStatus.getMoney() + CHICKENPRICE);
						}
						
						
					}
					if (eggCount > FarmSizeEnums.LARGE.eggMax) {
						
						int excedente = eggCount - FarmSizeEnums.LARGE.eggMax;
						
						//sellEgg(excedente, farmID);
						
						for (int i = 0; i < excedente; i++) {
							newEggList.remove(0);
							farmStatus.setMoney(farmStatus.getMoney() + EGGPRICE);
						}				
					}
					
				}
				//devuelvo las listas revisadas
				return Stream.concat(newChickenList.stream(), newEggList.stream()).collect(Collectors.toList());
	}
}
