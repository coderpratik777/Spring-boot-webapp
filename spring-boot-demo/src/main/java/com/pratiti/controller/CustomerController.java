package com.pratiti.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties.Registration;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pratiti.Exception.CustomerServiceException;
import com.pratiti.entity.Customer;
import com.pratiti.model.ActivationStatus;
import com.pratiti.model.CustomerData;
import com.pratiti.model.LoginData;
import com.pratiti.model.LoginStatus;
import com.pratiti.model.RegistrationStatus;
import com.pratiti.model.Status;
import com.pratiti.service.CustomerService;

@RestController
@CrossOrigin //no error if we hit same api from two different points
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	/*
	 * @PostMapping("/register") public RegistrationStatus addCustomer(@RequestBody
	 * Customer customer) { RegistrationStatus status=new RegistrationStatus(); try
	 * { int id=customerService.register(customer); status.setStatus(true);
	 * status.setMesssageIfAny("Customer Registered successfully");
	 * status.setRegisterCustomerId(id); //return "customer added succesfully"; }
	 * catch(CustomerServiceException e) { //return e.getMessage();
	 * 
	 * status.setStatus(false); status.setMesssageIfAny(e.getMessage()); }
	 * 
	 * return status;
	 * 
	 * }
	 */
	
	//below code is use to save the images of the user
	@PostMapping("/register")
	public RegistrationStatus addCustomer(CustomerData customerData) {
		System.out.println(customerData.getProfilePic().getOriginalFilename());
		RegistrationStatus status=new RegistrationStatus();
		try {
			
			//copying the uploaded image in a folder
			
			String uploadDir="D:\\Uploads\\";
			InputStream f1=customerData.getProfilePic().getInputStream();
			FileOutputStream f2=new FileOutputStream(uploadDir+customerData.getProfilePic().getOriginalFilename());
			FileCopyUtils.copy(f1, f2);
			
			
			Customer customer=new Customer();
			BeanUtils.copyProperties(customerData, customer);
			customer.setProfilePic(customerData.getProfilePic().getOriginalFilename());
			int id=customerService.register(customer);
			status.setStatus(true);
			status.setMesssageIfAny("Customer Registered successfully");
			status.setRegisterCustomerId(id);
			//return "customer added succesfully";
		}
		catch(IOException | CustomerServiceException e) {
			//return e.getMessage();

			status.setStatus(false);
			status.setMesssageIfAny(e.getMessage());
		}
		
		return status;
		
	}
	
	@GetMapping("/activate-account")
	public ActivationStatus activateCustomer(@RequestParam("id")int id) {
		ActivationStatus status=new ActivationStatus();
		try {
			customerService.setStatus(id); 
			status.setMesssageIfAny("Your account has been activated");
			status.setactivationStatus("Active");
			return status;
			
		}
		catch(CustomerServiceException e){
			status.setMesssageIfAny(e.getMessage());
			return status;
			
		}
	}
	
	@PostMapping("/login")
	public LoginStatus login (@RequestBody LoginData loginData) {
		LoginStatus status=new LoginStatus();
		try {
			Customer customer=customerService.login(loginData.getEmailAddress(),loginData.getPassword());
			status.setStatus(true);
			status.setId(customer.getId());
			status.setName(customer.getName());
			status.setId(customer.getId());
			return status;
			
		}catch(CustomerServiceException e) {
			status.setStatus(false);
			status.setMesssageIfAny(e.getMessage());
			return status;
			
		}
		
	}
	
	@GetMapping("/viewprofile")
	public Customer viewCustomer(@RequestParam("id") int id) {
		return customerService.viewCustomer(id);
	}
	

}
