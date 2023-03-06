package com.pratiti.model;

public class LoginStatus extends Status{
	
	private int id;
	private String name;
	
	//we can also keep the private Customer customer to return the whole data of the customers
	//getters and setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
