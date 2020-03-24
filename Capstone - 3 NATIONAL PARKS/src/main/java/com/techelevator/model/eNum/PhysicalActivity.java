package com.techelevator.model.eNum;

public enum PhysicalActivity {
	INACTIVE("inactive"), SEDENTARY("sedentary"), ACTIVE("active"), EXTREMELY_ACTIVE("extremely active");

	private String name;

	PhysicalActivity(String name) {
		this.name = name;

	}
	
	

	public String getName() {
		return name;
	}



	@Override
	public String toString() {
		return name;
	}

}
