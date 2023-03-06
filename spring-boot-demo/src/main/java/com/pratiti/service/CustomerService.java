package com.pratiti.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pratiti.Exception.CustomerServiceException;
import com.pratiti.entity.Customer;
import com.pratiti.entity.Customer.Status;
import com.pratiti.repository.AddressRepository;
import com.pratiti.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	
	public int register(Customer customer) {
		
		if(!customerRepository.existsByEmail(customer.getEmail())){
			customer.setStatus(Status.INACTIVE);
			customer.getAddress().setCustomer(customer);
			customerRepository.save(customer);
			//code to send an email after registration
			return customer.getId();
			}
		else {
			throw new CustomerServiceException("Customer already registered!");
		}
	}
	
	public void setStatus(int id) {
		Optional<Customer> customer=customerRepository.findById(id);
		if(customer.isPresent()){
			Customer customerData=customer.get();//here we get the customer data
			if(customerData.getStatus()==Status.ACTIVE) {
				throw new CustomerServiceException("Already account is Activated");
			}
			if(customerData.getStatus()!=Status.LOCKED) {
				
				customerData.setStatus(Status.ACTIVE);
			    customerRepository.save(customerData);
			}
			else
				throw new CustomerServiceException("Your account is loacked,please contact Admin.");
		}
		else
			throw new CustomerServiceException("Your account is not there");
	}
	
	public Customer login(String email,String password) {
		Optional<Customer> customer=customerRepository.findByEmail(email);
		if(customer.isPresent()) {
			Customer customerData=customer.get();
			if(customerData.getStatus()==Status.LOCKED) {
				throw new CustomerServiceException("Your account is locked contact Admin.");
			}
			if(customerData.getStatus()==Status.ACTIVE) {
				System.out.println(customerData.getPassword());
				System.out.println(password);
				if(customerData.getPassword().equals(password)) {
					return customerData;
				}
				else {
					throw new CustomerServiceException("Password in incorrect");
				}
			}
			else
				throw new CustomerServiceException("Your account is not activated yet!");
			
		}
		throw new CustomerServiceException("You are not registered yet");
	}
	
	public Customer viewCustomer(int id) {
		Optional<Customer> customer=customerRepository.findById(id);
		Customer customerData=customer.get();
		System.out.println(customer);
		return customerData;
		
		
	}
}
