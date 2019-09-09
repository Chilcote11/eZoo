package com.examples.ezoo.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * POJO used to represent USER_ROLES table in database
 * 
 * Uses an EmbeddedId representing a composite key
 * 
 * Contains instructions for Spring validation.
 * 	Messages found in ValidationMessages.properties 
 * 
 * @author Cory Chilcote
 *
 */
@Entity
@Table(name="USER_ROLES")
public class UserRole {

	@EmbeddedId
	private EmbeddedUserRole userRole = new EmbeddedUserRole();
	
	public UserRole() {}
	
	public UserRole(EmbeddedUserRole userRole) {
		this.userRole = userRole;
	}

	public EmbeddedUserRole getUserRole() {
		return userRole;
	}

	public void setUserRoles(EmbeddedUserRole userRole) {
		this.userRole = userRole;
	}

	@Override
	public String toString() {
		return "UserRole [username=" + userRole.getUsername() + 
							"role=" + userRole.getRole() + "]";
	}
	
	
}
