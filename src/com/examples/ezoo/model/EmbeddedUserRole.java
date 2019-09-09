package com.examples.ezoo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;

/**
 * An embedded object useful for working with the composite
 *  (username, role) key in the USER_ROLES database table
 *  
 * Used by UserRole class only
 * 
 * Contains instructions for Spring validation.
 * 	Messages found in ValidationMessages.properties 
 * 
 * @author Cory Chilcote
 *
 */
@Embeddable
public class EmbeddedUserRole implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "{username.validate}")
	private String username;
	
	@NotEmpty(message = "{role.validate}")
	private String role;
	
	public EmbeddedUserRole() {}
	
	public EmbeddedUserRole(String username, String role) {
		this.username = username;
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	@Override public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || this.getClass() != o.getClass()) return false;
		
		EmbeddedUserRole that = (EmbeddedUserRole) o;
		
		if (!username.equals(that.username)) return false;
		return role.equals(that.role);
	}
	
	@Override
	public int hashCode() {
		int result = username.hashCode();
		result = 31 * result + role.hashCode();
		return result;
	}
}
