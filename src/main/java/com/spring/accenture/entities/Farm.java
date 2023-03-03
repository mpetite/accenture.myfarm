package com.spring.accenture.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.spring.accenture.repositories.ChickenRepository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Status")
public class Farm {
//esta entity declara los detalles de una granja.
	
	@Autowired
	ChickenRepository chickenRepository;

	//declaro los campos necesarios
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long ID;
	
	@Column
	private String size;
	
	@Column
	private String name;
	
	@Column(name = "Farmers_id")
	private long farmerID;
	
	private List<Chicken> chickenList;

	//getters y setters

	public String getLocationID() {
		return name;
	}

	public void setLocationID(String locationID) {
		this.name = locationID;
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

	public long getFarmerID() {
		return farmerID;
	}
	
	public List<Chicken> getChickenList(int option){

		List<Chicken> productList = chickenRepository.findAllByFarmID(this.ID);
		List<Chicken> returnList = new ArrayList<>();

		switch (option) {
		case 0:
			return productList;

		case 1:
			for (Chicken item : productList) {
				if (!item.getIsEgg()) {
					returnList.add(item);
				}
			}
			return returnList;

		case 2:
			for (Chicken item : productList) {
				if (item.getIsEgg()) {
					returnList.add(item);
				}
			}
			return returnList;
			
		default: return productList;
		}
		
	}

	public void setChickenList(List<Chicken> chickenList) {
		this.chickenList = chickenList;
	}
}
