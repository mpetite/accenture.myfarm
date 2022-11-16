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

	
	public List<Chicken> getChickenList(int option) {

		List<Chicken> returnChickenList = null;
		
		switch (option) {
		case 0:
			returnChickenList = chickenList;
			

		case 1:
			for (Chicken item : chickenList) {
				if (!item.isEgg()) {
					returnChickenList.add(item);
				}
			}
			

		case 2:
			for (Chicken item : chickenList) {
				if (item.isEgg()) {
					returnChickenList.add(item);
				}
			}
		}
		
		return returnChickenList;
	}

	public void setChickenList(List<Chicken> chickenList) {
		this.chickenList = chickenList;
	}

}
