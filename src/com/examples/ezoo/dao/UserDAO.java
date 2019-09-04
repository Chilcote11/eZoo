package com.examples.ezoo.dao;

import com.examples.ezoo.model.User;

public interface UserDAO {

	void saveUser(User user) throws Exception;
	void deleteUser(User user) throws Exception;
	User getUserByName(String username);
}
