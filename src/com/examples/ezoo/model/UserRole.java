package com.examples.ezoo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// fields should not be modified after initialization, dao is not setup to handle that
// this is a very useless class and db table
@Entity
@Table(name="USER_ROLES")
public class UserRole {
	@Id private String username = "";
	@Column private String role = "";
	
	public UserRole(User user) {
		this.username = user.getUsername();
		this.role = user.getRole();
	}
}
