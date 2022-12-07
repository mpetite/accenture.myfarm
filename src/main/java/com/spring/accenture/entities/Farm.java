package com.spring.accenture.entities;

import java.util.ArrayList;
import java.util.List;

public class Farm {

	//declaro las variables necesarias
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

		List<Chicken> returnChickenList =  new ArrayList<Chicken>();
		
		switch (option) {
		case 0:
			returnChickenList = chickenList;
			break;
		case 1:
			
			for (Chicken item : chickenList) {
				if (!item.getIsEgg()) {
					returnChickenList.add(item);
				}

			}
			break;

		case 2:
			for (Chicken item : chickenList) {
				if (item.getIsEgg()) {
					returnChickenList.add(item);
				}
				
			}
			break;
		}
		return returnChickenList;
	}

	public void setChickenList(List<Chicken> chickenList) {
		this.chickenList = chickenList;
	}

}
