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
		Event event1 = new Event(400, "penguin flips", LocalDateTime.now(), "gnarly stuff");
		Event event2 = new Event(401, "dolphin flips", LocalDateTime.now(), "don't wanna miss this");
		Event event3 = new Event(402, "lion flips", LocalDateTime.now(), "watch out");
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
		for (Event e : allEvents) {				// I don't use for-each elsewhere.. not sure why
			System.out.println(e);
		}
		
		// test getEventByID
		Event event4 = dao.getEventByID(event3.getEventID()); // 402
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
			dao.leaveEvent(cory, event1);		// penguins not that big
		} catch (Exception e) {
			System.out.println(e);
		}
		
		// test getEventsByUser
		List<EventAttendee> corysEvents = dao.getEventsByUser(cory);
//		Collections.sort(corysEvents);
		System.out.println("Cory's events: ");
		for (EventAttendee e : corysEvents) {
			System.out.println(e);
		}
		
		// test deleteEvent						// all things back to normal
		try {
			for (Event e : allEvents) {
				dao.deleteEvent(e);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
