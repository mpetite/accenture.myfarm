package com.spring.accenture.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.accenture.entities.Chicken;
@Repository
public interface ChickenRepository extends JpaRepository<Chicken, Long> {

	List<Chicken> findAllByFarmID(long farmID);
	
	List<Chicken> findAllByisEgg(boolean b);

}
