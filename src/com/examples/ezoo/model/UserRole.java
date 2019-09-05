package com.examples.ezoo.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// fields should not be modified after initialization, dao is not setup to handle that
// this is a very useless class and db table
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
