package com.examples.ezoo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name="USERS")
public class User implements Comparable<User> {

	@Id 
	@NotEmpty(message = "{username.validate}")
	private String username = "";

	@Column @NotEmpty(message = "{password.validate}")
	private String password = "";
	
	@Transient // not an actual column in db.. obviously
	PasswordEncoder encoder = new BCryptPasswordEncoder();
	
	@Column 
	@NotEmpty(message = "{role.validate")
	private String role = "";
	
//	@Transient 		// not a property in "users" database table
//	// this is only used in UserDAOImpl
//	private UserRole roleObject = new UserRole(this);
	
	// nested class for inserting user roles objects into database
	/*@Entity
	@Table(name="USER_ROLES")
	private class UserRole {
		@Id @Column(name="username")
		private String name = username;
		@Column(name="role") private String userRole = role;
		
		public UserRole(User user) {
		}
	}*/
	
	public User() {}
	
	public User(String username, String password, String role) {
		super();
		this.username = username;
//		this.password = password;
		this.password = encoder.encode(password);
		this.role = role;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	// this getter probably shouldn't exist. but needed to run with Spring
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
//		this.password = password;
		this.password = encoder.encode(password);
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
//	public UserRole getRoleObject() {
//		return roleObject;
//	}

	
	@Override
	public int compareTo(User o) {
		// TODO 
		return 0;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", role=" + role + "]";
	}

	
}
