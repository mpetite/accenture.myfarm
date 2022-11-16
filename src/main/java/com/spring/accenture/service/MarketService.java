package com.spring.accenture.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.spring.accenture.entities.Chicken;
import com.spring.accenture.entities.Status;
import com.spring.accenture.exceptions.InsufficientFundsException;
import com.spring.accenture.exceptions.InsufficientStorageException;

public class MarketService {

	public static final double CHICKENPRICE = 35;
	public static final double EGGPRICE = 1;

	@Autowired
	private static ChickenService chickenService;

	@Autowired
	private static StatusService statusService;

	public static void sellChicken(int amount, long farmID) {

		// Consigo el tamaño de la granja
		Status farmStatus = statusService.getStatus(farmID);

		// fijo las ganancias totales
		double totalWinning = CHICKENPRICE * amount;
		// si
		if (
		// hay suficientes vacas
		(farmStatus.getCattleCount() > 7))
		// entonces:
		{
			// borro la cantidad de gallinas
			for (int byeChicken = 1; byeChicken < amount; byeChicken++) {
				chickenService.deleteChicken(farmID);
			}
			// """"transfiero"""" el dinero
			farmStatus.setMoney(farmStatus.getMoney() + totalWinning);
			statusService.saveStatus(farmStatus);
		}
	}

	public static void buyChicken(int amount, long farmID)
			throws InsufficientFundsException, InsufficientStorageException {

		// Consigo la cantidad de gallinas en la granja y el tamaño de la misma
		int chickenCount = chickenService.findProducts(farmID, 1).size();
		Status farmStatus = statusService.getStatus(farmID);

		// fijo el precio total
		double totalPrice = CHICKENPRICE * amount;
		// si:
		if (
		// el conteo de gallinas + la cantidad a comprar no excede el tamaño de la
		// granja
		(farmStatus.getSize().equalsIgnoreCase("Medium") && chickenCount < 50
				|| farmStatus.getSize().equalsIgnoreCase("Small") && chickenCount < 24
				|| farmStatus.getSize().equalsIgnoreCase("Large") && chickenCount < 100)) {
			// y si el usuario tiene suficeinte dinero
			if (farmStatus.getMoney() - totalPrice >= 0)
			// entonces:
			{
				// creo la cantidad de gallinas
				for (int newChicken = 1; newChicken < amount; newChicken++) {
					Chicken chicken = new Chicken(farmID, 32);
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
		}
	}

	public static void sellEgg(int amount, long farmID) {
		// Consigo status de granja
		Status farmStatus = statusService.getStatus(farmID);

		// fijo las ganancias totales
		double totalWinning = EGGPRICE * amount;

		// si:
		if (
		// hay suficientes vacas
		(farmStatus.getCattleCount() > 4))
		// entonces:
		{
			// borro la cantidad de gallinas
			for (int byeChicken = 1; byeChicken < amount; byeChicken++) {
				chickenService.deleteChicken(farmID);

			}
			// """"transfiero"""" el dinero
			statusService.getStatus(farmID).setMoney(farmStatus.getMoney() + totalWinning);
			statusService.saveStatus(farmStatus);
		}
	}

	public static void buyEgg(int amount, long farmID) 
			throws InsufficientFundsException, InsufficientStorageException {

		// Consigo la cantidad de gallinas en la granja y el tamaño de la misma
		int eggCount = chickenService.findProducts(farmID, 2).size();
		Status farmStatus = statusService.getStatus(farmID);

		// fijo el precio total
		double totalPrice = EGGPRICE * amount;
		// si:
		if (
		// el conteo de gallinas + la cantidad a comprar no excede el tamaño de la
		// granja
				(farmStatus.getSize().equalsIgnoreCase("Medium") && eggCount < 2000 ||
						  farmStatus.getSize().equalsIgnoreCase("Small") && eggCount < 1000 ||
						  farmStatus.getSize().equalsIgnoreCase("Large") && eggCount < 3000)) {
			// y si el usuario tiene suficeinte dinero
			if (farmStatus.getMoney() - totalPrice >= 0)
			// entonces:
			{
				// creo la cantidad de gallinas
				for (int newEgg = 1; newEgg < amount; newEgg++) {
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
	
}



/*
 * // Consigo la cantidad de pollos en la granja y el tamaño de la misma int
 * eggCount = chickenService.findProducts(farmID, 2).size(); Status farmStatus =
 * statusService.getStatus(farmID);
 * 
 * // fijo el precio total double totalPrice = EGGPRICE * amount; // si: if ( //
 * el conteo de gallinas + la cantidad a comprar no excede el tamaño de la //
 * granja (farmStatus.getSize().equals("Medium") && eggCount < 2000 ||
 * farmStatus.getSize().equals("Small") && eggCount < 1000 ||
 * farmStatus.getSize().equals("Large") && eggCount < 3000)
 * 
 * // y si el usuario tiene suficeinte dinero && (farmStatus.getMoney() -
 * totalPrice >= 0)) // entonces: { // creo la cantidad de huevos for (int
 * newEgg = 1; newEgg < amount; newEgg++) { Chicken egg = new Chicken(farmID,
 * 0); chickenService.saveChicken(egg); } // """"transfiero"""" el dinero
 * farmStatus.setMoney(farmStatus.getMoney() - totalPrice);
 * statusService.saveStatus(farmStatus); } } }
 */