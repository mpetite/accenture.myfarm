package com.spring.accenture.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Status")
public class Status {
//esta entity declara los detalles de una granja.

	//declaro los campos necesarios
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long ID;
	
	@Column
	private double money;
	
	@Column
	private String size;
	
	@Column
	private String locationID;
	

	//getters y setters
	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String getLocationID() {
		return locationID;
	}

	public void setLocationID(String locationID) {
		this.locationID = locationID;
	}

	public long getID() {
		return ID;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String newSize) {
		size = newSize;
	}

}
