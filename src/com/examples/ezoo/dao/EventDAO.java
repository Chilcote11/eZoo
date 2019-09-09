package com.examples.ezoo.dao;

import java.util.List;

import com.examples.ezoo.model.Event;
import com.examples.ezoo.model.EventAttendee;
import com.examples.ezoo.model.User;

/**
 * Main interface used to execute CRUD methods on Event class
 * 
 * @author Cory Chilcote
 * @version 2.0
 * @since 1.0
 *
 */
public interface EventDAO {
	
	// Security: for users and admins
	Event getEventByID(Integer eventID);							// check out an event
	List<Event> getAllEvents();										// view zoo schedule
	List<EventAttendee> getEventsByUser(User user);					// see events you're signed up for
	void signUpForEvent(User user, Event event) throws Exception;	// sign up for event
	void leaveEvent(User user, Event event) throws Exception;		// leave event
	int getNumberAttending(int eventID);							// see how many attending an event

	// Security: for admins only
	void saveEvent(Event event) throws Exception;					// create new event
	void deleteEvent(Event event) throws Exception;					// delete an event
	void updateEvent(Event event) throws Exception;					// update an event
}
