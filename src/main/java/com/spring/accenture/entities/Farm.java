package com.spring.accenture.entities;

import java.util.List;

public class Farm {

	// declaro las variables necesarias
	private long ID;

	private Status status;

	private List<Chicken> chickenList;
	
	
	
	
	
//getters y setters
	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<Chicken> getChickenList() {
		return chickenList;
	}

	public void setChickenList(List<Chicken> chickenList) {
		this.chickenList = chickenList;
	}
	
	
}
