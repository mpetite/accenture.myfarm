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

	public List<Chicken> findAllChickens(long farmID) {

		return repo.findAllByFarmID(farmID);

	}
	
}
