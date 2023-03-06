package com.pratiti.model;

import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

import com.pratiti.entity.Address;
//we are defining the another class because we want to separate the Persisttence layer and controller layer
public class CustomerData {
	
	private String name;
	private LocalDate dateOfBirth;
	private String email;
	private String password;
	
	//whenever we want to upload we use multiparte
	
	private MultipartFile profilePic;
	
	private Address address=new Address();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public MultipartFile getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(MultipartFile profilePic) {
		this.profilePic = profilePic;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	
}
