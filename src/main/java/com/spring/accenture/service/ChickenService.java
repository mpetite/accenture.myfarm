package com.spring.accenture.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.accenture.entities.Chicken;
import com.spring.accenture.repositories.ChickenRepository;

@Service
public class ChickenService {
//Servicio que se encarga de lo que pueden/se les puede hacer a los chicken

	private ChickenRepository chickenRepository;

	private FarmService farmService;

	// metodos de busqueda de chicken

	public List<Chicken> findAllLivestock() {
		return (List<Chicken>) chickenRepository.findAll();
	}

	// manejo de chicken
	public void saveChicken(Chicken c) {
		chickenRepository.save(c);
	}

	public void deleteChicken(long farmID) {
		// consigo un ID aleatorio entre los ids de las gallinas

		long randChickenID = farmService.getFarmByID(farmID).getChickenList(1).get(0).getID();

		// lo borro
		chickenRepository.deleteById(randChickenID);
	}

	public void deleteEgg(long farmID) {
		// consigo un ID aleatorio entre los ids de las gallinas
		long randEggID = farmService.getFarmByID(farmID).getChickenList(2).get(0).getID();

		// lo borro
		chickenRepository.deleteById(randEggID);
	}

	public void saveAllLivestock(List<Chicken> chickenList) {
		chickenRepository.saveAll(chickenList);
	}
}
