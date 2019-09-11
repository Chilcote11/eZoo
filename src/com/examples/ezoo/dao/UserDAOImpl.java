package com.examples.ezoo.dao;

import java.util.List;

import org.apache.logging.log4j.Level;
import org.hibernate.SessionFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.examples.ezoo.logger.Origin;
import com.examples.ezoo.logger.ZooLogger;
import com.examples.ezoo.model.EmbeddedUserRole;
import com.examples.ezoo.model.User;
import com.examples.ezoo.model.UserRole;

/**
 * Uses Spring and Hibernate functions to perform CRUD operations
 * on User class
 * 
 * Documentation for each method provided in UserDAO interface
 * 
 * @author Cory Chilcote
 *
 */
@Repository			// this class acts as a database repository
@Transactional 		// tells Spring to create a proxy with this interface
public class UserDAOImpl implements UserDAO {
	
	private ZooLogger Log = new ZooLogger();
	
	PasswordEncoder encoder = new BCryptPasswordEncoder();
	
	private SessionFactory sessionFactory;		// from Spring

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	@Override
	public void saveUser(User user) throws Exception {
		
		// encode the password before saving
		String newPassword = encoder.encode(user.getPassword());
		user.setPassword(newPassword);
		
		// save into USERS table
		sessionFactory.getCurrentSession().save(user);
		
		// save into USER_ROLES table
		for (String role : user.getRoles()) {
			UserRole ur = new UserRole(new EmbeddedUserRole(user.getUsername(), role));
			sessionFactory.getCurrentSession().save(ur);
		}
		
		Log.daoLog(Origin.USERDAO_SAVE, Level.DEBUG, 
				"save " + user.getUsername() + "[" + user.getRoles() +  "]");
	}
	
	@Override 
	public void deleteUser(User user) throws Exception {
		
		// encode the password before deleting
		user.setPassword(encoder.encode(user.getPassword()));
		
		// delete from USER_ROLES table
		for (String role : user.getRoles()) {
			UserRole ur = new UserRole(new EmbeddedUserRole(user.getUsername(), role));
			sessionFactory.getCurrentSession().delete(ur);
		}
		
		// TODO delete from EVENT_ATTENDEES
		
		// delete from USERS table
		sessionFactory.getCurrentSession().delete(user);
		
		
		Log.daoLog(Origin.USERDAO_DELETE, Level.DEBUG, 
				"delete " + user.getUsername() + "[" + user.getRoles() +  "]");
	}
	
	@Override
	public User getUserByName(String username) {
		
		if (username == null) { 					// assuming I'll need this
			return null;
		}
		
		User user = sessionFactory.getCurrentSession().get(User.class, username);
		
		Log.daoLog(Origin.USERDAO_GETBYNAME, Level.DEBUG, user);
		
		return user;
	}

}
