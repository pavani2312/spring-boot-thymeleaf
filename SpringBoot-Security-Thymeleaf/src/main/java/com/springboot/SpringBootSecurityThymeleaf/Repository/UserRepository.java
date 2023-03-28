package com.springboot.SpringBootSecurityThymeleaf.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.springboot.SpringBootSecurityThymeleaf.Model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	
	User findByUsernameAndPassword(String username, String password);
	User findByusername(String username);

	@Modifying
	@Transactional
	@Query( "update User set password =:password where username =:userName")
	Integer updatePassword(String userName, String password);
}
