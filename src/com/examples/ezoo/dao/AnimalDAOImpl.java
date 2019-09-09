package com.examples.ezoo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.query.Query;
import org.apache.logging.log4j.Level;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.examples.ezoo.logger.Origin;
import com.examples.ezoo.logger.ZooLogger;
import com.examples.ezoo.model.Animal;

/**
 * Uses Spring and Hibernate functions to perform CRUD operations
 * on Animal class
 * 
 * Documentation for each method provided in AnimalDAO interface
 * 
 * @author Cory Chilcote
 *
 */
@Repository			// this class acts as a database repository
@Transactional 		// tells Spring to create a proxy with this interface
public class AnimalDAOImpl implements AnimalDAO {
	
	private ZooLogger Log = new ZooLogger();
	
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
		
		Log.daoLog(Origin.ANIMALDAO_GETALL, Level.DEBUG, 
				animals.size() + " retrieved");
		
		return animals;
	}

	@Override
	public void saveAnimal(Animal animal) throws Exception {
		sessionFactory.getCurrentSession().save(animal);
		
		Log.daoLog(Origin.ANIMALDAO_SAVE, Level.DEBUG, 
				"save " + animal.getName() + "[" + animal.getAnimalID() +  "]");
	}
	
	@Override
	public void deleteAnimal(Animal animal) throws Exception {
		
		System.out.println("animal.getName()" + animal.getName());
		
		Log.daoLog(Origin.ANIMALDAO_DELETE, Level.DEBUG, 
				"delete " + animal.getName() + "[" + animal.getAnimalID() +  "]");
		
		sessionFactory.getCurrentSession().delete(animal);
	}

}
