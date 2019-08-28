package com.examples.ezoo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
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
	
	@Transient 		// not a property in "users" database table
	// this is only used in UserDAOImpl
	private UserRole roleObject = new UserRole(this);
	
	// nested class for inserting user roles objects into database
	@Entity
	@Table(name="USER_ROLES")
	private class UserRole {
		@Id private String username = "";
		@Column private String userRole = "";
		
		public UserRole(User user) {
			this.userRole = user.userRole;
			this.username = user.username;
		}
	}
	
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
	
	public UserRole getRoleObject() {
		return roleObject;
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
