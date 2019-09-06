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

	// TODO: find appropriate pattern to require passwords to have two numbers
		// most of the attempts below work in simulators (regexr, etc).. but not here
	@Column @NotEmpty(message = "{password.notempty.validate}")
	@Pattern(regexp="[$./\\w\\d]{8,}", message = "{password.length.validate}")
//	@Pattern(regexp="(\\w)*(\\d)(\\w)*(\\d)", message = "{password.numbers.validate}")				// "ConstraintViolationException: Validation failed for classes.. during persist time for groups.."
//	@Pattern(regexp="($./\\w)*(\\d)($./\\w)*(\\d)", message = "{password.numbers.validate}")		// "11" passes.  "111", "11a", "aaa11" all do not
//	@Pattern(regexp="(\\D)*(\\d)(\\D)*(\\d)", message = "{password.numbers.validate}")				// "11", "a11", "a1a1" all pass. "11a", "a1a1a", "a1a1a1" all do not pass
//	@Pattern(regexp="(\\D)*(\\d)(\\D)*(\\d)+($./\\w\\d)*", message = "{password.numbers.validate}")	// same as above
//	@Pattern(regexp="(\\D)*(\\d)(\\D)*(\\d)($./\\w\\d)*", message = "{password.numbers.validate}")	// same as above
//	@Pattern(regexp="[(\\D)*(\\d)(\\D)*(\\d)] {1,}", message = "{password.numbers.validate}")		// same as above
	@Pattern(regexp="[(\\D)*(\\d)(\\D)*(\\d)]+", message = "{password.numbers.validate}")			// everything passes except "".  numbers not required 
//	@Pattern(regexp="^(?=.*?[0-9]{2,})$", message = "{password.numbers.validate}")					// nothing passes this requirement
//	@Pattern(regexp="^(?=.*?[0-9]{2,}).{8,}$", message = "{password.numbers.validate}")				// numbers must be next to eachother. "aaaaaa11", "11aa..", "aa11aaaa" pass, "aaaaa1a1" does not
//	@Pattern(regexp="(?=.*?[0-9]{2,}).{8,}", message = "{password.numbers.validate}")				// same as above
	private String password = "";
		
	@Transient
	@NotEmpty(message = "{role.validate}")
	private List<String> roles = new ArrayList<>();
		
	// at one point I considered nesting this class, 
	// but ultimately had trouble because of the way fields are set with spring forms
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
		this.roles = roles;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	// this getter probably shouldn't exist. but needed to run with Spring
		// TODO: actually haven't checked this... just an assumption
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
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
