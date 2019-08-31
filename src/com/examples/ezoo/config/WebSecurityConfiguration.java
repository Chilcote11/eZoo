package com.examples.ezoo.config;

import javax.sql.DataSource;
import javax.ws.rs.HttpMethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled=true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/")
				.failureUrl("/login?error=true")
				//.failureUrl("/login?message=User+successfully+created&messageClass=alert-success")
				.permitAll()
				.and()
			.authorizeRequests()
			
				// anyone can see the home page
				.antMatchers("/").permitAll()
				
				// prevent ROLE_USERs from accessing all pages but animal care, ROLE_ADMINs can see all
				.antMatchers("/AnimalAdd*", "/FSAssign*", "/FSCreate*"
						, "/feedingSchedules*", "/FSUpdate*")
					.hasAuthority("ROLE_ADMIN")

				// block post methods as well --- but none of these work
				.antMatchers(HttpMethod.POST, "AnimalAdd*")
					.hasAuthority("ROLE_ADMIN")
				.antMatchers(HttpMethod.POST, "FSAssign*")
					.hasAuthority("ROLE_ADMIN")
				.antMatchers(HttpMethod.POST, "FSUnassign*")	// doesn't work
					.hasAuthority("ROLE_ADMIN")					
				.antMatchers(HttpMethod.POST, "FSCreate*")
					.hasAuthority("ROLE_ADMIN")
				.antMatchers(HttpMethod.POST, "FSDelete*")	// doesn't work
					.hasAuthority("ROLE_ADMIN")
				.antMatchers(HttpMethod.POST, "FSUpdate*")
					.hasAuthority("ROLE_ADMIN")
				
				// everyone must be authenticated to get past the home page
				.anyRequest().authenticated()
				.and()
			.logout()
				.logoutSuccessUrl("/")
				.and()
				
			// configure accessDeniedPage location
			.exceptionHandling()
				.accessDeniedPage("/WEB-INF/views/accessDenied.jsp")
		;
		
		// prevent USERs from accessing all pages but animal care, ADMINs can see all
		http
			.authorizeRequests()
				.antMatchers("/addAnimal", "/assignFeedingSchedule", "/createFeedingSchedule"
						, "/feedingSchedules", "/updateFeedingSchedule")
					.hasAuthority("ROLE_ADMIN")
		;
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		
//		// allow login messages to pass
//		web.ignoring().antMatchers("/login?message=User+successfully+created&messageClass=alert-success");
//		
//		// allow login error url to pass
//		web.ignoring().antMatchers("/login?error=true");
		
//		web.ignoring().antMatchers("/login*");

		// allow registration page to pass
		web.ignoring().antMatchers("/register");
		
		// allow formatting pages to pass
		web.ignoring().antMatchers("/resources/**", "/WEB-INF/views/header", "/WEB-INF/views/footer");
	}
	
	@Autowired
	DataSource ds;	// I think this will work?
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
				.withUser("user")
					.password("{noop}password")
					.authorities("ROLE_USER")
					.and()
				.withUser("admin")
					.password("{noop}passw0rd")
					//.authorities("ROLE_USER", "ROLE_ADMIN");
					.authorities("ROLE_ADMIN");
		
		auth
			.jdbcAuthentication()
				.dataSource(ds)
//				.usersByUsernameQuery("SELECT username, password, active FROM USERS WHERE username = ?") // keep "active"?
				.usersByUsernameQuery("SELECT username, password, true FROM users WHERE username = ?")
				.authoritiesByUsernameQuery("SELECT username, role FROM user_roles WHERE username = ?")
//				.groupAuthoritiesByUsername("")
				.passwordEncoder(new BCryptPasswordEncoder())
				;
	}

}
