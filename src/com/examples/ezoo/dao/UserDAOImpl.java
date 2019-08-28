package com.examples.ezoo.dao;

import org.apache.logging.log4j.Level;
import org.hibernate.SessionFactory;

import com.examples.ezoo.logger.Origin;
import com.examples.ezoo.logger.ZooLogger;
import com.examples.ezoo.model.User;

public class UserDAOImpl implements UserDAO {
	
	private ZooLogger Log = new ZooLogger();
	
	private SessionFactory sessionFactory;		// from Spring

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	@Override
	public void saveUser(User user) throws Exception {
		sessionFactory.getCurrentSession().save(user);
		sessionFactory.getCurrentSession().save(user.getRoleObject());
		
		Log.daoLog(Origin.USERDAO_SAVE, Level.DEBUG, 
				"save " + user.getUsername() + "[" + user.getUserRole() +  "]");
	}
	
	@Override public void deleteUser(User user) throws Exception {
		sessionFactory.getCurrentSession().delete(user);
		sessionFactory.getCurrentSession().delete(user.getRoleObject());
		
		Log.daoLog(Origin.USERDAO_DELETE, Level.DEBUG, 
				"delete " + user.getUsername() + "[" + user.getUserRole() +  "]");
	}

}
