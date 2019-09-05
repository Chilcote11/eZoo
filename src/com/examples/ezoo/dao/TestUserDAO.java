package com.examples.ezoo.dao;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.examples.ezoo.config.Config;
import com.examples.ezoo.model.User;

public class TestUserDAO {
	
	public static void main(String[] args) {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		UserDAO dao = (UserDAO) context.getBean(UserDAO.class);
		
		// test saveUser method
		User user = new User("user22", "password", "ROLE_USER");
		User admin = new User("admin22", "passw0rd", "ROLE_ADMIN");
		User another = new User();		
		another.setUsername("another22");
		another.setPassword("password");
		another.setRole("ROLE_USER");
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
