package com.springboot.SpringBootSecurityThymeleaf.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.SpringBootSecurityThymeleaf.Model.User;
import com.springboot.SpringBootSecurityThymeleaf.Service.CustomerService;

@RestController
public class MainController {
	
	@Autowired
	private CustomerService customerService;

	@GetMapping("/")
	public String hello() {
		return "This is Home page";
	}
	
	@GetMapping("/saveuser")
	public String saveUser(@RequestParam String username, @RequestParam String fullName, @RequestParam String email, @RequestParam String password) {
		User user = new User(username, fullName, email, password, null);
		customerService.save(user);
		return "User Saved";
	}

}
