package com.spring.accenture.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.accenture.entities.Status;
import com.spring.accenture.repositories.StatusRepository;
@Service
public class StatusService {

	@Autowired
	private StatusRepository repo;

	public Status getStatus(long objectID) {

		// llamar al repo de status
		// return info del repo extraída
		Optional<Status> repoReturn = repo.findById(objectID);
		return repoReturn.orElse(null);

	}
}
