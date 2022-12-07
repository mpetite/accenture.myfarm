package com.spring.accenture.entities;

import javax.persistence.*;


@Entity
public class Chicken {
//esta entidad se encarga de los huevos y los pollos

	//genero los campos necesarios
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long ID;
	
	@Column(name = "farmID")
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
		this.isEgg = age <= 10;
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

	public void setIsEgg(boolean a) {
		this.isEgg = a;
	}
	
	
	//Metodos necesarios
	//metodo para el incremento de la edad de la entidad
	public void increaseAge() {
		this.ageDays = ageDays + 1; 
		this.isEgg = ageDays <= 10;
	}
}
