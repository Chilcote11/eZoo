package com.examples.ezoo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.authorizeRequests()
				.antMatchers("/").permitAll()
				.anyRequest().authenticated()
				.and()
			.logout()
				.logoutSuccessUrl("/");
		
		http
			.authorizeRequests()
				.antMatchers("/register").permitAll();
	}
	
	@Autowired
	DataSource ds;	// I think this will work?
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
				.withUser("user")
					.password("password")
					.authorities("ROLE_USER")
					.and()
				.withUser("admin")
					.password("passw0rd")
					.authorities("ROLE_USER", "ROLE_ADMIN");
		
		auth
			.jdbcAuthentication()
				.dataSource(ds)
//				.usersByUsernameQuery("SELECT username, password, active FROM USERS WHERE username = ?") // keep "active"?
				.usersByUsernameQuery("SELECT username, password FROM users WHERE username = ?")
				.authoritiesByUsernameQuery("SELECT username, role FROM user_roles WHERE username = ?")
//				.groupAuthoritiesByUsername("")
				.passwordEncoder(new BCryptPasswordEncoder())
				;
	}

}
