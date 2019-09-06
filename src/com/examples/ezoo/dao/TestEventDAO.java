package com.examples.ezoo.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.examples.ezoo.config.Config;
import com.examples.ezoo.model.Event;
import com.examples.ezoo.model.EventAttendee;
import com.examples.ezoo.model.User;


/*
 * This test class assumes that a user 'cory' exists in USERS table
 * When finished running, all data is restored to its original state
 * */
public class TestEventDAO {

	public static void main(String[] args) {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		EventDAO dao = (EventDAO) context.getBean(EventDAO.class);
		UserDAO userDAO = (UserDAO) context.getBean(UserDAO.class);
		
		// test saveEvent method
		Event event1 = new Event(400, "penguin flips", "gnarly stuff", LocalDateTime.now().plusHours(2), 
				LocalDateTime.now().plusHours(2), userDAO.getUserByName("cory").getUsername());
		Event event2 = new Event(401, "dolphin flips", "don't wanna miss this", LocalDateTime.now().plusHours(2), 
				LocalDateTime.now().plusHours(2), userDAO.getUserByName("cory").getUsername());
		Event event3 = new Event(402, "lion flips", "watch out", LocalDateTime.now().plusHours(2), 
				LocalDateTime.now().plusHours(2), userDAO.getUserByName("cory").getUsername());
		try {
			dao.saveEvent(event1);
			dao.saveEvent(event2);
			dao.saveEvent(event3);
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		
		// test updateEvent method
		event1.setEventName("big penguin flips");
		try {
			dao.updateEvent(event1);
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		
		// test getAllEvents
		List<Event> allEvents = dao.getAllEvents();
		Collections.sort(allEvents);
		System.out.println("All events: ");
		for (Event e : allEvents) {
			System.out.println(e);
		}
		
		// test getEventByID
		Event event4 = dao.getEventByID(event3.getEventID()); 	// 402
		System.out.println("test getEventByID");
		System.out.println("event4: " + event4);
		System.out.println(" 			= 			");
		System.out.println("event3: " + event3);

		// test signUpForEvent
		User cory = userDAO.getUserByName("cory");
		try {
			for (Event e : allEvents) {				// season tickets
				dao.signUpForEvent(cory, e);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
				
		// test leaveEvent 400
		try {
			dao.leaveEvent(cory, event1);		// penguins not that impressive
		} catch (Exception e) {
			System.out.println(e);
		}

		// test getNumberAttending
		for (Event e : allEvents) {
			System.out.println("Number attending " + e.getEventName() + "[" + 
				e.getEventID() + "]: " + dao.getNumberAttending(e.getEventID()));
		}
		
		// test getEventsByUser
		List<EventAttendee> corysEvents = dao.getEventsByUser(cory);
		System.out.println("Cory[" + cory + "]'s events: ");
		for (EventAttendee e : corysEvents) {
			System.out.println(e);
		}
		
		// test deleteEvent						// all things back to original state
		try {
			for (Event e : allEvents) {
				dao.deleteEvent(e);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		context.close();
	}

}
