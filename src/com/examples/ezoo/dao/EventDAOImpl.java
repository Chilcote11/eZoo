package com.examples.ezoo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.examples.ezoo.logger.ZooLogger;
import com.examples.ezoo.model.EmbeddedEventAttendee;
import com.examples.ezoo.model.Event;
import com.examples.ezoo.model.EventAttendee;
import com.examples.ezoo.model.User;

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
	public Event getEvent(Integer eventID) {
		
		if (eventID == null) {					// assuming I'll need this
			return null;
		}
		
		Event event = sessionFactory.getCurrentSession().get(Event.class, eventID);
		
		return event;
	}

	@Override
	public List<Event> getAllEvents() {
		Session session = sessionFactory.openSession();
		Query<Event> results = session.createQuery("from Event");
		List<Event> events = results.list();
		session.close();
		
		return events;
	}

	@Override
	public List<Event> getEventsByUser(User user) {				// not confident about this one
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query<Event> query = session.createQuery("from EventAttendee where username = :UNAME");
		query.setParameter("UNAME", user.getUsername());
		query.executeUpdate();
		List<Event> events = query.list();			// not sure if I can do this
		session.getTransaction().commit();			// also fairly sure this is out of order
		session.close();
		
		// TODO logging
		
		return events;
	}

	@Override
	public void signUpForEvent(User user, Event event) throws Exception {
		EventAttendee ea = new EventAttendee(new EmbeddedEventAttendee(user.getUsername(), event.getEventID()));
		// can move EmbeddedEventAttendee (EEA) to EventAttendee (EA) if EA only used in DAO.. TBD
		sessionFactory.getCurrentSession().save(ea);
		
		// TODO logging
	}

	@Override
	public void leaveEvent(User user, Event event) throws Exception {
		EventAttendee ea = new EventAttendee(new EmbeddedEventAttendee(user.getUsername(), event.getEventID()));
		sessionFactory.getCurrentSession().delete(ea);
		
		// TODO logging
	}

	@Override
	public void saveEvent(Event event) throws Exception {
		sessionFactory.getCurrentSession().save(event);
		
		// TODO logging

	}

	@Override
	public void deleteEvent(Event event) throws Exception {
		sessionFactory.getCurrentSession().delete(event);
		
		// TODO logging

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
		
		// TODO logging

	}

}
