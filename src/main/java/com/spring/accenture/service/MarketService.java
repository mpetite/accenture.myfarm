package com.spring.accenture.service;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;

import com.spring.accenture.entities.Chicken;

public class MarketService {

	public static final double CHICKENPRICE = 5;
	public static final double EGGPRICE = 2;

	@Autowired
	private static ChickenService chickenService;

	@Autowired
	private static StatusService statusService;

	public static void sellChicken(int amount, long farmID) {

	}

	public static void buyChicken(int amount, long farmID) {

		// Consigo la cantidad de pollos ya en esa granja y el tamaño de la misma
		int chickenCount = chickenService.findAllChickens(farmID).size();
		String fSize = statusService.getStatus(farmID).getSize();
		double statusMoney = statusService.getStatus(farmID).getMoney();

		// si el conteo de gallinas + la cantidad a comprar no excede el tamaño de la granja
		if (fSize.equals("Medium")) {

			// fijo el precio total
			double totalPrice = CHICKENPRICE * amount;

			// creo la cantidad de chickens
			for (int newChicken = 1; newChicken < amount; newChicken++) {

				Chicken chicken = new Chicken(farmID);
			}
			
			//""""transfiero"""" el dinero
			statusService.getStatus(farmID).setMoney(statusMoney - totalPrice);
		}
	}

	public static void sellEgg(int amount, long farmID) {

	}

	public static void buyEgg(int amount, long farmID) {

	}
}
