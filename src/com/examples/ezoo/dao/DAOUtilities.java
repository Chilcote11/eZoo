package com.examples.ezoo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Class used to retrieve DAO Implementations. Serves as a factory.
 * 
 * @author anon
 *
 */
public class DAOUtilities {

	private static final String CONNECTION_USERNAME = "postgres";
	private static final String CONNECTION_PASSWORD = "password";
	private static final String URL = "jdbc:postgresql://localhost:5432/eZoo";
	
	private static AnimalDAOImpl animalDAOImpl;
	private static FeedingScheduleDAOImpl feedingScheduleDAOImpl;
	private static Connection connection;

	public static synchronized AnimalDAO getAnimalDAO() {
		// returns AnimalDAO interface implemented by animalDaoImpl.  protects in the face of future upgrades (different implementations)
		// to better understand this, see the notes in the last part of 100 Dive 2

		if (animalDAOImpl == null) {
			animalDAOImpl = new AnimalDAOImpl();
		}
		return animalDAOImpl;
	}
	
	public static synchronized FeedingScheduleDAO getFeedingScheduleDAO() {
		if (feedingScheduleDAOImpl == null) {
			feedingScheduleDAOImpl = new FeedingScheduleDAOImpl();
		}
		return feedingScheduleDAOImpl;
	}

	static synchronized Connection getConnection() throws SQLException {
		if (connection == null) {
			try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("Could not register driver!");
				e.printStackTrace();
			}
			connection = DriverManager.getConnection(URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
		}
		
		//If connection was closed then retrieve a new connection
		if (connection.isClosed()){
			System.out.println("getting new connection...");
			connection = DriverManager.getConnection(URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
		}
		return connection;
	}

}
