package com.spring.accenture.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Farmer {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String name;
	
	private double wallet;

	
	
	//constructores
	
	public Farmer() {}
	
	public Farmer(String fname, double money) {
		
		this.name = fname;
		this.wallet = money;
		
	}
	
	
	//getters y setters
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getWallet() {
		return wallet;
	}

	public void setWallet(double wallet) {
		this.wallet = wallet;
	}

	public long getId() {
		return id;
	}
	
	
	
}
