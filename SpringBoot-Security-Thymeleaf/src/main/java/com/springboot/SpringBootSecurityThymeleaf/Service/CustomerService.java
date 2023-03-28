package com.springboot.SpringBootSecurityThymeleaf.Service;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.springboot.SpringBootSecurityThymeleaf.Model.Role;
import com.springboot.SpringBootSecurityThymeleaf.Model.User;
import com.springboot.SpringBootSecurityThymeleaf.Repository.UserRepository;

@Service
public class CustomerService implements UserDetailsService {

	private UserRepository userRepository;

	public CustomerService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	
    //For Registration
	public User save(User user) {
		User user1 = new User(user.getUsername(), user.getFullName(), user.getEmail(),
				passwordEncoder.encode(user.getPassword()), Arrays.asList(new Role("ROLE USER")));
		return userRepository.save(user1);
	}

	//For Password Reset
	public Integer resetPassword(String username, String password) {
		return userRepository.updatePassword(username, password);
	}
	
	//For User Login
	public User loginUser(String username, String password) {
		return userRepository.findByUsernameAndPassword(username, password);
	}
	
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByusername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not Found");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				CustomUserDetails.mapRolesToAuthorities(user.getRoles()));
	}

}
