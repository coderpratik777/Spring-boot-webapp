package com.pratiti.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@RequestMapping("/hello")
	public String Hello() {
		return "Welcome to spring Boot and REST API DEVELOPMENT";
	}
}