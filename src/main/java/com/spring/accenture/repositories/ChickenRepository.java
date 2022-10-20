package com.spring.accenture.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.accenture.entities.Chicken;
@Repository
public interface ChickenRepository extends CrudRepository<Chicken, Long> {

	List<Chicken> findAllByFarmID(long farmID);

}
