package com.examples.ezoo.dao;

import org.hibernate.SessionFactory;

import com.examples.ezoo.model.User;

public class UserDAOImpl implements UserDAO {
	
	private SessionFactory sessionFactory;		// from Spring

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	@Override
	public void addUser(User user) throws Exception {
		sessionFactory.getCurrentSession().save(user);
	}

}
