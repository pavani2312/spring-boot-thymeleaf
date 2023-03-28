package com.springboot.SpringBootSecurityThymeleaf.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.springboot.SpringBootSecurityThymeleaf.Model.RegisterUser;
import com.springboot.SpringBootSecurityThymeleaf.Model.User;
import com.springboot.SpringBootSecurityThymeleaf.Service.CustomerService;

@Controller
public class ApplicationController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@GetMapping("/register")
	public String showRegistration() {
		return "register";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/password")
	public String password() {
		return "password";
	}

	/*
	 * @GetMapping("/home") public String homePage() { return "home"; }
	 */

	@ModelAttribute("user")
	public User registerNewUser() {
		return new User();
	}

	@RequestMapping("/registration")
	public String registerUserAccount(@ModelAttribute("user") User user, Model model, BindingResult result) {
		customerService.save(user);

		return "redirect:/register?success";
	}
	
	@RequestMapping("/passwordreset")
	public String updateThePassword(@ModelAttribute RegisterUser registerUser, BindingResult result) {
		customerService.resetPassword(registerUser.getUsername(), passwordEncoder.encode(registerUser.getPassword()));
		return "login";

	}


	@RequestMapping ("/loginUser")
	public String loginUser(@ModelAttribute User user) {
		if(customerService.loadUserByUsername(user.getUsername())!=null) //
		{
			return "home";
		}
		else 
		return "login";
	}
	
//	@PostMapping("/login_failed")
//	public String loginFailure() {
//		return "redirect:/login?error";
//	}

}
