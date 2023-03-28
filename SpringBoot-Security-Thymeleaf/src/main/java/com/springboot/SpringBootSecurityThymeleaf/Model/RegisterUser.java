package com.springboot.SpringBootSecurityThymeleaf.Model;

public class RegisterUser {
	
	private String username;

	private String fullName;

	private String email;

	private String password;

	public RegisterUser(String username, String fullName, String email, String password) {
		super();
		this.username = username;
		this.fullName = fullName;
		this.email = email;
		this.password = password;
	}

	public RegisterUser() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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
	

}
