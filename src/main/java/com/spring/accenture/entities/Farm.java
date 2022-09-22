package com.spring.accenture.entities;


import javax.persistence.*;

@Entity
public class Farm {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ID;
	
	@Column(nullable = false, unique = false)
	private String Location;
	
	@Column(nullable = true)
	private int cattleCount;
	
	@Column(nullable = true)
	private int chickenCount;
		
	@Column(nullable = true)
	private int eggCount;
	
	
}
