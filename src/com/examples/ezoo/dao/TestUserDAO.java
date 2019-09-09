package com.examples.ezoo.dao;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.examples.ezoo.config.Config;
import com.examples.ezoo.model.User;

/**
 * Test script to verify FeedingScheduleDAOImpl methods 
 * 
 * Assumptions:
 * 		+ Usernames 'user22', 'admin22', and 'another22' do not already exist in the database
 * 
 * This test class makes changes to the database, 
 * 	but resets all values to their original state upon completion
 * 
 * @author Cory Chilcote
 * 
 */
public class TestUserDAO {
	
	public static void main(String[] args) {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		UserDAO dao = (UserDAO) context.getBean(UserDAO.class);
		
		// test saveUser method
		User user = new User("user22", "password", Arrays.asList("ROLE_USER"));
		User admin = new User("admin22", "passw0rd", Arrays.asList("ROLE_USER", "ROLE_ADMIN"));
		User another = new User();		
		another.setUsername("another22");
		another.setPassword("password");
		another.setRoles(Arrays.asList("ROLE_USER"));
		try {
			dao.saveUser(user);
			dao.saveUser(admin);
			dao.saveUser(another);
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		
		// test deleteUser method
		try {
			dao.deleteUser(user);
			dao.deleteUser(admin);
			dao.deleteUser(another);
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		
		context.close();
	}

}
