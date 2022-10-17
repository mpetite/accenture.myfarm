package com.spring.accenture.entities;

import javax.persistence.*;

import com.spring.accenture.service.FarmService;

@Entity
public class Chicken {

	
	FarmService farmService = new FarmService();
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ID;
	
	@Column(nullable = false, unique = false)
	private String name;
	
	@Column(nullable=false)
	private int age;
	
	@Column(nullable=false)
	private int ageDays;
	
	@Column(nullable=false)
	private boolean isEgg = ageDays <= 31;


	
	/*@Override
	public static void buy() {
		
	}*/
	
}
