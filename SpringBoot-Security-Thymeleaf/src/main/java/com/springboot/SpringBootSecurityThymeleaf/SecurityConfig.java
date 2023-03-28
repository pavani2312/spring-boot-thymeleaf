package com.springboot.SpringBootSecurityThymeleaf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
  AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
		daoProvider.setUserDetailsService(userDetailsService);
		daoProvider.setPasswordEncoder(passwordEncoder());
		return daoProvider;
	  
  }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http.authorizeRequests()
		       .antMatchers("/loginUser").authenticated() //.anyRequest()
		       .antMatchers("/login", "/register").permitAll()
	           .and()
	           .formLogin()
	           .loginPage("/login")
	           .loginProcessingUrl("/doLogin")
	           .defaultSuccessUrl("/home", true)
	           .failureUrl("/login?error=true")
	           .and()
	           .logout()
	           .invalidateHttpSession(true)
	           .clearAuthentication(true)
	           .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	           .logoutSuccessUrl("/login?logout")
	           .permitAll();
	}
	


//   .failureForwardUrl("/login_failed")
	//http.authorizeRequests()
//         .antMatchers("/register**", "/js/**",
//      		   "/css/**","/img/**","/password","/home","/registration").permitAll()
//         .anyRequest().authenticated()
//         .and()
//         .formLogin()
//         .loginPage("/login")
//         .permitAll()
//         .and()
//         .logout()
//         .invalidateHttpSession(true)
//         .clearAuthentication(true)
//         .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//         .logoutSuccessUrl("/login?logout")
//         .permitAll();
	
	
}
