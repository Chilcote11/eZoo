package com.examples.ezoo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="ANIMALS")
public class User implements Comparable<User> {

	@Id 
	@NotEmpty(message = "{username.validate}")
	private String username = "";

	@Column @NotEmpty(message = "{password.validate}")
	private String password = "";
	
	@Column(name="user_role") 
	@NotEmpty(message = "{user_role.validate")
	private String userRole;
	
	public User() {}
	
	public User(String username, String password, String userRole) {
		super();
		this.username = username;
		this.password = password;
		this.userRole = userRole;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	
	@Override
	public int compareTo(User o) {
		// TODO 
		return 0;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", userRole=" + userRole + "]";
	}

	
}
