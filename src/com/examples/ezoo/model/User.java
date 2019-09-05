package com.examples.ezoo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name="USERS")
public class User implements Comparable<User> {

	@Id 
	@NotEmpty(message = "{username.validate}")
	private String username = "";

	@Column @NotEmpty(message = "{password.notempty.validate}")
	@Pattern(regexp="[$./\\w\\d]{8,}", message = "{password.length.validate}")
//	@Pattern(regexp="($./\\w)*(\\d)($./\\w)*(\\d)", message = "{password.numbers.validate}")		// "11" passes.  "111", "11a", "aaa11" all do not
//	@Pattern(regexp="(\\D)*(\\d)(\\D)*(\\d)", message = "{password.numbers.validate}")				// "11", "a11", "a1a1" all pass. "11a", "a1a1a", "a1a1a1" all do not pass
//	@Pattern(regexp="(\\D)*(\\d)(\\D)*(\\d)+($./\\w\\d)*", message = "{password.numbers.validate}")	// same as above
//	@Pattern(regexp="(\\D)*(\\d)(\\D)*(\\d)($./\\w\\d)*", message = "{password.numbers.validate}")	// same as above
//	@Pattern(regexp="[(\\D)*(\\d)(\\D)*(\\d)] {1,}", message = "{password.numbers.validate}")		// same as above
	@Pattern(regexp="[(\\D)*(\\d)(\\D)*(\\d)]+", message = "{password.numbers.validate}")			// everything passes except "".  numbers not required 
	private String password = "";
	
//	@Transient // not an actual column in db.. obviously
//	PasswordEncoder encoder = new BCryptPasswordEncoder();
	
	@Transient
	@NotEmpty(message = "{role.validate}")
	private List<String> roles = new ArrayList<>();
	
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
	
	public User(String username, String password, List<String> roles) {
		super();
		this.username = username;
		this.password = password;
//		this.password = encoder.encode(password);
		this.roles = roles;
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
		this.password = password;
//		this.password = encoder.encode(password);
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> role) {
		this.roles = roles;
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
		return "User [username=" + username + ", password=" + password + ", roles=" + roles + "]";
	}

	
}
