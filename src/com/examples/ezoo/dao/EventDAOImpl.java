package com.examples.ezoo.dao;

import java.util.List;

import org.apache.logging.log4j.Level;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.examples.ezoo.logger.Origin;
import com.examples.ezoo.logger.ZooLogger;
import com.examples.ezoo.model.EmbeddedEventAttendee;
import com.examples.ezoo.model.Event;
import com.examples.ezoo.model.EventAttendee;
import com.examples.ezoo.model.User;

@Repository
@Transactional
public class EventDAOImpl implements EventDAO {
	
	private ZooLogger Log = new ZooLogger();
	
	private SessionFactory sessionFactory;		// from Spring
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Event getEventByID(Integer eventID) {
		
		if (eventID == null) {					// assuming I'll need this
			return null;
		}
		
		Event event = sessionFactory.getCurrentSession().get(Event.class, eventID);
		
		Log.daoLog(Origin.EVENTDAO_GETBYID, Level.DEBUG, event);
		
		return event;
	}

	@Override
	public List<Event> getAllEvents() {
		Session session = sessionFactory.openSession();
		Query<Event> results = session.createQuery("from Event");
		List<Event> events = results.list();
		session.close();
		
		Log.daoLog(Origin.EVENTDAO_GETALL, Level.DEBUG, 
				": " + events.size() + " retrieved");
		
		return events;
	}

	@Override
	public List<EventAttendee> getEventsByUser(User user) {				// not confident about this one
		Session session = sessionFactory.openSession();
//		session.beginTransaction();
		Query<EventAttendee> query = session.createQuery("from EventAttendee WHERE username = :UNAME");
		query.setParameter("UNAME", user.getUsername());
//		query.executeUpdate();
		List<EventAttendee> events = query.list();			// not sure if I can do this
//		session.getTransaction().commit();			// also fairly sure this is out of order
		session.close();
		
		Log.daoLog(Origin.EVENTDAO_GETEVENTSFORUSER, Level.DEBUG, 
				events.size() + " events retrieved for " + user.getUsername());
		
		return events;
	}

	@Override
	public void signUpForEvent(User user, Event event) throws Exception {
		EventAttendee ea = new EventAttendee(new EmbeddedEventAttendee(user.getUsername(), event.getEventID()));
		// can move EmbeddedEventAttendee (EEA) to EventAttendee (EA) if EA only used in DAO.. TBD
		sessionFactory.getCurrentSession().save(ea);
		
		Log.daoLog(Origin.EVENTDAO_SIGNUP, Level.DEBUG, 
				"user: " + user.getUsername() +  
				", event: " + event.getEventID());
	}

	@Override
	public void leaveEvent(User user, Event event) throws Exception {
		EventAttendee ea = new EventAttendee(new EmbeddedEventAttendee(user.getUsername(), event.getEventID()));
		sessionFactory.getCurrentSession().delete(ea);
		
		Log.daoLog(Origin.EVENTDAO_LEAVE, Level.DEBUG, 
				"user: " + user.getUsername() +  
				", event: " + event.getEventID());
	}

	@Override
	public void saveEvent(Event event) throws Exception {
		sessionFactory.getCurrentSession().save(event);
		
		Log.daoLog(Origin.EVENTDAO_SAVE, Level.DEBUG, event);

	}

	@Override
	public void deleteEvent(Event event) throws Exception {
		
		// remove from EVENT_ATTENDEES table
//		EmbeddedEventAttendee eea = new EmbeddedEventAttendee();
//		eea.setEventID(event.getEventID());
//		EventAttendee ea = new EventAttendee(eea);
//		sessionFactory.getCurrentSession().delete(ea);
		
		// remove from EVENT_ATTENDEES table
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("DELETE FROM EventAttendee WHERE event_id = :EI");
		query.setParameter("EI", event.getEventID());
		query.executeUpdate();
		session.getTransaction().commit();			// also fairly sure this is out of order
		session.close();
		
		// remove from EVENTS table
		sessionFactory.getCurrentSession().delete(event);
		
		Log.daoLog(Origin.EVENTDAO_DELETE, Level.DEBUG, event);

	}

	@Override
	public void updateEvent(Event event) throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("UPDATE Event SET "
				+ "name = :N, date = :T, description = :D WHERE event_id = :EI");
		query.setParameter("N", event.getEventName());
		query.setParameter("T", event.getEventDate());
		query.setParameter("D", event.getDescription());
		query.setParameter("EI", event.getEventID());
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();
		
		Log.daoLog(Origin.EVENTDAO_UPDATE, Level.DEBUG, event);

	}

}