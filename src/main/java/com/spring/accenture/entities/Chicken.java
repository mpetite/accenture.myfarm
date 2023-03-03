package com.spring.accenture.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Chicken")
public class Chicken {
//esta entidad se encarga de los huevos y los pollos

	//genero los campos necesarios
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long ID;
	
	@Column(name = "Status_id")
	private long farmID;
	
	@Column
	private int ageDays;
	
	@Column
	private boolean isEgg;
	
	
	//constructores
	public Chicken() {
		super();
	}
	
	public Chicken(long farmID, int age) {
		this.farmID = farmID;
		this.ageDays = age;
		this.isEgg = age < 10;
	}
	
	
	//getters y setters
	public long getFarmId() {
		return farmID;
	}

	public void setFarmId(long farmID) {
		this.farmID = farmID;
	}

	public int getAgeDays() {
		return ageDays;
	}

	public void setAgeDays(int ageDays) {
		this.ageDays = ageDays;
	}

	public long getID() {
		return ID;
	}

	public boolean getIsEgg() {
		return isEgg;
	}

	public void setIsEgg() {
		this.isEgg = this.ageDays >10;
	}

}
