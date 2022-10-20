package com.spring.accenture.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Farmer {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long ID;
	
	@Column(nullable = false, unique = false)
	private String name;
	
	@Column(nullable=false)
	private int money;
	
	@Column(nullable=true)
	private long[] locationsOwned;
	
	

}
