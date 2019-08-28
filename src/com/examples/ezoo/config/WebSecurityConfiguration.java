package com.examples.ezoo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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
	}

}
