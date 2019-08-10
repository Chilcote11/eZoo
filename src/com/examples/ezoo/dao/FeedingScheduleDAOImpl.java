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
import com.examples.ezoo.model.FeedingSchedule;

@Repository
@Transactional
public class FeedingScheduleDAOImpl implements FeedingScheduleDAO{
	
	private SessionFactory sessionFactory;		// from Spring

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override		// checked and tested
	public void saveFeedingSchedule(FeedingSchedule feedingSchedule) throws Exception {
		sessionFactory.getCurrentSession().save(feedingSchedule);
		
		/*Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO feeding_schedules VALUES (?, ?, ?, ?, ?)";
			
			// Setup PreparedStatement
			stmt = connection.prepareStatement(sql);
			
			// Add parameters from feeding_schedules into PreparedStatement
			stmt.setInt(1,  feedingSchedule.getScheduleID());
			stmt.setString(2,  feedingSchedule.getFeedingTime());
			stmt.setString(3,  feedingSchedule.getRecurrence());
			stmt.setString(4,  feedingSchedule.getFood());
			stmt.setString(5,  feedingSchedule.getNotes());
			
			success = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (success == 0) {
			// then update didn't occur, throw an exception
			throw new Exception("Save feeding schedule failed: " + feedingSchedule);
		}*/
	}

	@Override
	public void deleteFeedingSchedule(FeedingSchedule feedingSchedule) throws Exception {
		sessionFactory.getCurrentSession().delete(feedingSchedule);
		
		/*Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "DELETE FROM feeding_schedules WHERE schedule_id = ?";
			
			// Setup PreparedStatement
			stmt = connection.prepareStatement(sql);
			
			// Add parameters from feeding_schedules into PreparedStatement
			stmt.setInt(1,  feedingSchedule.getScheduleID());
			
			success = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (success == 0) {
			throw new Exception("Delete feeding schedule failed: +" + feedingSchedule);
		}*/
	}

	@Override // checked
	public List<FeedingSchedule> getAllFeedingSchedules() {
		Session session = sessionFactory.openSession();
		Query<FeedingSchedule> results = session.createQuery("from FeedingSchedule");
		List<FeedingSchedule> feedingSchedules = results.list();		
		
		/*List<FeedingSchedule> feedingSchedules = new ArrayList<>();
		Connection connection = null;
		Statement stmt = null;

		try {
			connection = DAOUtilities.getConnection();

			stmt = connection.createStatement();

			String sql = "SELECT * FROM feeding_schedules";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				FeedingSchedule fs = new FeedingSchedule();

				fs.setScheduleID(rs.getInt("schedule_id"));
				fs.setFeedingTime(rs.getString("feeding_time"));
				fs.setRecurrence(rs.getString("recurrence"));
				fs.setFood(rs.getString("food"));
				fs.setNotes(rs.getString("notes"));
								
				feedingSchedules.add(fs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}*/
		return feedingSchedules;
	}

	@Override
	public FeedingSchedule getFeedingSchedule(Animal animal) {
		
		if (animal.getFeedingScheduleID() == null) {
			return null;
		}
		
		Session session = sessionFactory.openSession();		
		FeedingSchedule feedingSchedule = sessionFactory.getCurrentSession().get(FeedingSchedule.class, animal.getFeedingScheduleID());
		
		/*FeedingSchedule feedingSchedule = new FeedingSchedule();
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			connection = DAOUtilities.getConnection();
				
			String sql = "SELECT * FROM feeding_schedules WHERE schedule_id = ?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setInt(1, animal.getFeedingScheduleID());

			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				feedingSchedule.setScheduleID(rs.getInt("schedule_id"));
				feedingSchedule.setFeedingTime(rs.getString("feeding_time"));
				feedingSchedule.setRecurrence(rs.getString("recurrence"));
				feedingSchedule.setFood(rs.getString("food"));
				feedingSchedule.setNotes(rs.getString("notes"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}*/

		return feedingSchedule;
		
	}

	@Override
	public void assignFeedingSchedule(FeedingSchedule feedingSchedule, Animal animal) throws Exception {
		
		Session session = sessionFactory.openSession();	
		session.beginTransaction();
		Query query = session.createQuery("UPDATE Animal SET feedingScheduleID = :Schedule_ID WHERE animalID = :Animal_ID");
		query.setParameter("Schedule_ID", feedingSchedule.getScheduleID());
		query.setParameter("Animal_ID", animal.getAnimalID());
		query.executeUpdate();
		session.getTransaction().commit();
		
		/*Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "UPDATE animals SET feeding_schedule = ? WHERE animalid = ?";
			
			// Setup PreparedStatement
			stmt = connection.prepareStatement(sql);
			
			// Add parameters from animals into PreparedStatement
			stmt.setInt(1,  feedingSchedule.getScheduleID());
			stmt.setLong(2, animal.getAnimalID());
			
			success = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (success == 0)
			throw new Exception("Assign feeding schedule failed: " + animal);*/
	}

	@Override
	public void removeFeedingSchedule(Animal animal) throws Exception { 		// remove means set = null?
		
		Session session = sessionFactory.openSession();	
		session.beginTransaction();
		Query query = session.createQuery("UPDATE Animal SET feedingScheduleID = :Schedule_ID WHERE animalID = :Animal_ID");
		query.setParameter("Schedule_ID", null);
		query.setParameter("Animal_ID", animal.getAnimalID());
		query.executeUpdate();
		session.getTransaction().commit();
		
		/*Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "UPDATE animals SET feeding_schedule = null WHERE animalid = ?";
			
			// Setup PreparedStatement
			stmt = connection.prepareStatement(sql);
			
			// Add parameters from feeding_schedules into PreparedStatement
			stmt.setLong(1,  animal.getAnimalID());
			
			success = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (success == 0) {
			throw new Exception("Remove feeding schedule from animal failed: +" + animal);
		}*/
	}
	
	@Override
	public void updateFeedingSchedule(FeedingSchedule feedingSchedule) throws Exception {
		
		Session session = sessionFactory.openSession();	
		session.beginTransaction();
		Query query = session.createQuery("UPDATE FeedingSchedule SET feedingTime = :FT, "
				+ "recurrence = :R, food = :F, notes = :N WHERE scheduleID = :ID");
		query.setParameter("FT", feedingSchedule.getFeedingTime());
		query.setParameter("R", feedingSchedule.getRecurrence());
		query.setParameter("F", feedingSchedule.getFood());
		query.setParameter("N", feedingSchedule.getNotes());
		query.setParameter("ID", feedingSchedule.getScheduleID());
		query.executeUpdate();
		session.getTransaction().commit();
		
		/*Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "UPDATE feeding_schedules SET feeding_time = ?, recurrence = ?,"
					+ " food = ?, notes = ? WHERE schedule_id = ?";
			
			// Setup PreparedStatement
			stmt = connection.prepareStatement(sql);
			
			// Add parameters from animals into PreparedStatement
			stmt.setString(1,  feedingSchedule.getFeedingTime());
			stmt.setString(2,  feedingSchedule.getRecurrence());
			stmt.setString(3,  feedingSchedule.getFood());
			stmt.setString(4,  feedingSchedule.getNotes());
			stmt.setInt(5,  feedingSchedule.getScheduleID());
			
			success = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (success == 0)
			throw new Exception("Update feeding schedule failed: " + feedingSchedule);*/
	}

}
