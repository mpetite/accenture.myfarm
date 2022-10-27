package com.spring.accenture.service;

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

		List<Chicken> farmRepo = repo.findAllByFarmID(farmID);

		// preguntar a lucho: (ChickenRepository) farmRepo) = "Cast" ??
		return ((ChickenRepository) farmRepo).findAllByisEgg(true);
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
