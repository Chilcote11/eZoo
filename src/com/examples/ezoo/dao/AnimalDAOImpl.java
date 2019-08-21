package com.examples.ezoo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.examples.ezoo.model.Animal;

@Repository
@Transactional
public class AnimalDAOImpl implements AnimalDAO {
	
	private SessionFactory sessionFactory;		// from Spring

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Animal> getAllAnimals() {
		Session session = sessionFactory.openSession();
		Query<Animal> results = session.createQuery("from Animal");		// can omit SELECT in HQL
		List<Animal> animals = results.list();
		session.close();
		return animals;
	}

	@Override
	public void saveAnimal(Animal animal) throws Exception {
//		sessionFactory.getCurrentSession().save(animal);
		sessionFactory.openSession().save(animal);
		sessionFactory.getCurrentSession().close();
	}

}
