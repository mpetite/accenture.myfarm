package com.spring.accenture.entities;

public enum FarmSizeEnums {

	SMALL(24, 100),
	MEDIUM(50, 200),
	LARGE(100, 400);

	public final int chickenMax;
	
	public final int eggMax;
	
	FarmSizeEnums(int chickenMax, int eggMax) {
		this.chickenMax = chickenMax;
		this.eggMax = eggMax;
	}
}
