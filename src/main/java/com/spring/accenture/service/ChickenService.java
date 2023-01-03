package com.spring.accenture.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.accenture.entities.Chicken;
import com.spring.accenture.repositories.ChickenRepository;

@Service
public class ChickenService {
//Servicio que se encarga de lo que pueden/se les puede hacer a los chicken
	@Autowired
	private ChickenRepository repo;
	//metodos de busqueda de chicken
	public List<Chicken> findProducts(long farmID, int product) {

		List<Chicken> productList = repo.findAllByFarmID(farmID);
		List<Chicken> returnList = new ArrayList<>();

		switch (product) {
		case 0:
			return productList;

		case 1:
			for (Chicken item : productList) {
				if (!item.getIsEgg()) {
					returnList.add(item);
				}
			}
			return returnList;

		case 2:
			for (Chicken item : productList) {
				if (item.getIsEgg()) {
					returnList.add(item);
				}
			}
			return returnList;
			
		default: return productList;
		}
		
	}

	public List<Chicken> findAllLivestock() {
		return (List<Chicken>)repo.findAll();
	}
	
	//manejo de chicken
	public void saveChicken(Chicken c) {
		repo.save(c);
	}

	public void deleteChicken(long farmID) {
		// consigo un ID aleatorio entre los ids de las gallinas
		/*long randChickenID = (long) Math.random() * (0 - findAllChickens(farmID).size() + 1)
				+ findAllChickens(farmID).size();*/
		long randChickenID = findProducts(farmID, 1).get(0).getID();
		
		// lo borro
		repo.deleteById(randChickenID);
	}

	public void deleteEgg(long farmID) {
		// consigo un ID aleatorio entre los ids de las gallinas
		/*long randEggID = (long) Math.random() * (0 - findAllEggs(farmID).size() + 1) + findAllEggs(farmID).size();
		*/
		long randEggID = findProducts(farmID, 2).get(0).getID();
		
		// lo borro
		repo.deleteById(randEggID);
	}

	public void saveAllLivestock(List<Chicken> chickenList) {
		repo.saveAll(chickenList);
	}
	
	public void replaceAllLivestock(List<Chicken> chickenList) {
		repo.deleteAll();
		repo.saveAll(chickenList);
	}
}
