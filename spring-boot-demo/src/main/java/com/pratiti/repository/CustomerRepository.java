package com.pratiti.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pratiti.entity.Customer;

public interface CustomerRepository extends  JpaRepository<Customer, Integer> {
	
//	@Query("select c from Cutomer c where c.email=?1")
//	public Optional<Customer> findByEmail(String email);
	
	public boolean existsByEmail(String email);
	
//	@Query("select c from Customer c where email=?1")
	public Optional<Customer> findByEmail(String email);
	
	public Optional<Customer> findById(int id);
	
}
