package com.examples.ezoo.dao;

import com.examples.ezoo.model.User;

/**
 * Main interface used to execute CRUD methods on User class
 * 
 * @author Cory Chilcote
 *
 */
public interface UserDAO {

	void saveUser(User user) throws Exception;				// save a user to the database
	void deleteUser(User user) throws Exception;			// delete a user
	User getUserByName(String username);					// get a user by name
	
}
