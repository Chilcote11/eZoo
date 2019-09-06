package com.examples.ezoo.dao;

import com.examples.ezoo.model.User;

public interface UserDAO {

	void saveUser(User user) throws Exception;				// save a user to the database
	void deleteUser(User user) throws Exception;			// delete a user
	User getUserByName(String username);					// get a user by name
	
}
