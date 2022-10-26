package com.spring.accenture.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.spring.accenture.entities.Chicken;
import com.spring.accenture.entities.Status;

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
				// *TODO*: falta el saveState
			}
			// """"transfiero"""" el dinero
			statusService.getStatus(farmID).setMoney(farmStatus.getMoney() + totalWinning);
		}
	}

	public static void buyChicken(int amount, long farmID) {

		// Consigo la cantidad de gallinas en la granja y el tamaño de la misma
		int chickenCount = chickenService.findAllChickens(farmID).size();
		Status farmStatus = statusService.getStatus(farmID);

		// fijo el precio total
		double totalPrice = CHICKENPRICE * amount;
		// si:
		if (
		// el conteo de gallinas + la cantidad a comprar no excede el tamaño de la
		// granja
		(farmStatus.getSize().equals("Medium") && chickenCount < 50
				|| farmStatus.getSize().equals("Small") && chickenCount < 24
				|| farmStatus.getSize().equals("Large") && chickenCount < 100)

				// y si el usuario tiene suficeinte dinero
				&& (farmStatus.getMoney() - totalPrice >= 0))
		// entonces:
		{
			// creo la cantidad de gallinas
			for (int newChicken = 1; newChicken < amount; newChicken++) {
				Chicken chicken = new Chicken(farmID, 32);
				chickenService.saveChicken(chicken);
			}
			// """"transfiero"""" el dinero
			statusService.getStatus(farmID).setMoney(farmStatus.getMoney() - totalPrice);
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
				// *TODO*: falta el saveState
			}
			// """"transfiero"""" el dinero
			statusService.getStatus(farmID).setMoney(farmStatus.getMoney() + totalWinning);
		}
	}

	public static void buyEgg(int amount, long farmID) {

		// Consigo la cantidad de pollos en la granja y el tamaño de la misma
		int eggCount = chickenService.findAllEggs(farmID).size();
		Status farmStatus = statusService.getStatus(farmID);

		// fijo el precio total
		double totalPrice = EGGPRICE * amount;
		// si:
		if (
		// el conteo de gallinas + la cantidad a comprar no excede el tamaño de la
		// granja
		(farmStatus.getSize().equals("Medium") && eggCount < 2000
				|| farmStatus.getSize().equals("Small") && eggCount < 1000
				|| farmStatus.getSize().equals("Large") && eggCount < 3000)

				// y si el usuario tiene suficeinte dinero
				&& (farmStatus.getMoney() - totalPrice >= 0))
		// entonces:
		{
			// creo la cantidad de huevos
			for (int newEgg = 1; newEgg < amount; newEgg++) {
				Chicken egg = new Chicken(farmID, 0);
				chickenService.saveChicken(egg);
			}
			// """"transfiero"""" el dinero
			statusService.getStatus(farmID).setMoney(farmStatus.getMoney() - totalPrice);
		}
	}
}
