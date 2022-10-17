package com.spring.accenture.entities;


import javax.persistence.*;

@Entity
public class Farm {

	//declaro las variables necesarias
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ID;
	
	@Column(nullable = false, unique = false)
	private String LocationID;
	
	@Column(nullable = true)
	private int cattleCount;
	
	@Column(nullable = true)
	private int chickenCount;
		
	@Column(nullable = true)
	private int eggCount;
	
	@Column(nullable = true)
	private int size;
	
	
	//Pongo un solo constructor que pida la ubicacion (por ID de la granja)
	public Farm(String locationID) {
		super();
		LocationID = locationID;
	}
	
	
	//getters y setters
	//ID, Location y size solo tienen getter
	public int getCattleCount() {
		return cattleCount;
	}

	public void setCattleCount(int cattleCount) {
		this.cattleCount = cattleCount;
	}

	public int getChickenCount() {
		return chickenCount;
	}

	public void setChickenCount(int chickenCount) {
		this.chickenCount = chickenCount;
	}

	public int getEggCount() {
		return eggCount;
	}

	public void setEggCount(int eggCount) {
		this.eggCount = eggCount;
	}

	public int getID() {
		return ID;
	}

	public String getLocation() {
		return LocationID;
	}

	public int getSize() {
		return size;
	}
	
	
	
	
	
}
