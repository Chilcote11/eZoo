package com.examples.ezoo.dao;

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
	}

	@Override
	public void deleteFeedingSchedule(FeedingSchedule feedingSchedule) throws Exception {
		sessionFactory.getCurrentSession().delete(feedingSchedule);
	}

	@Override // checked
	public List<FeedingSchedule> getAllFeedingSchedules() {
//		Session session = sessionFactory.openSession();				// need to close? no warning..
		Session session = sessionFactory.getCurrentSession();		// should fix exception I get when opening feedingSchedules.jsp
		Query<FeedingSchedule> results = session.createQuery("from FeedingSchedule");
		List<FeedingSchedule> feedingSchedules = results.list();
//		session.close();
		return feedingSchedules;
	}

	@Override
	public FeedingSchedule getFeedingSchedule(Animal animal) {
		
		if (animal.getFeedingScheduleID() == null) {
			return null;
		}
				
		FeedingSchedule feedingSchedule = sessionFactory.getCurrentSession().get(FeedingSchedule.class, animal.getFeedingScheduleID());
		
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
	}
}
