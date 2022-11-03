package com.spring.accenture.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.accenture.entities.Chicken;
import com.spring.accenture.repositories.ChickenRepository;

@Service
public class ChickenService {

	@Autowired
	private ChickenRepository repo;

	public List<Chicken> findAllChickensAndEggsByFarmID(long farmID) {
		return repo.findAllByFarmID(farmID);
	}

	public List<Chicken> findAllChickens(long farmID) {

		List<Chicken> farmRepo = repo.findAllByFarmID(farmID);

		// Copié lo de find eggs pero para gallinas
		return ((ChickenRepository) farmRepo).findAllByisEgg(false);
	}

	public List<Chicken> findAllEggs(long farmID) {

		List<Chicken> chickenList = repo.findAllByFarmID(farmID);

		// preguntar a lucho: (ChickenRepository) farmRepo) = "Cast" ??
		return ((ChickenRepository) chickenList).findAllByisEgg(true);
	}

	public List<Chicken> findProducts(long farmID, int product) {

		List<Chicken> productList = repo.findAllByFarmID(farmID);
		List<Chicken> returnList = new ArrayList<>();

		switch (product) {
		case 0:
			returnList = productList;

		case 1:
			for (Chicken item : productList) {
				if (!item.isEgg()) {
					returnList.add(item);
				}
			}

		case 2:
			for (Chicken item : productList) {
				if (item.isEgg()) {
					returnList.add(item);
				}
			}
		}

		return returnList;
	}

	public void saveChicken(Chicken c) {
		repo.save(c);
	}

	public void deleteChicken(long farmID) {
		// consigo un ID aleatorio entre los ids de las gallinas
		long randChickenID = (long) Math.random() * (0 - findAllChickens(farmID).size() + 1)
				+ findAllChickens(farmID).size();
		// lo borro
		repo.deleteById(randChickenID);
	}

	public void deleteEgg(long farmID) {
		// consigo un ID aleatorio entre los ids de las gallinas
		long randEggID = (long) Math.random() * (0 - findAllEggs(farmID).size() + 1) + findAllEggs(farmID).size();
		// lo borro
		repo.deleteById(randEggID);
	}

}