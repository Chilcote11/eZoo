package com.examples.ezoo.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.examples.ezoo.config.Config;
import com.examples.ezoo.dao.EventDAO;
import com.examples.ezoo.logger.Origin;
import com.examples.ezoo.logger.ZooLogger;
import com.examples.ezoo.model.Event;
import com.examples.ezoo.model.EventAttendee;
import com.examples.ezoo.model.User;

/**
 * Allows users to see all events they've signed up for
 * 
 * @author Cory Chilcote
 *
 */
@Controller
public class PersonalEventsController {

	private ZooLogger Log = new ZooLogger();
	
	/**
	 * Prepares attributes for the personal events page
	 * 
	 * @param model
	 * @return view name
	 */
	@RequestMapping(value="/PersonalEvents", method=RequestMethod.GET)
	public String DisplayCalendar(Model model) {
		
		Log.controllerLog(Origin.CONTROLLER_PERSONALEVENTS_GET, Level.INFO, "navigation");
		
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		EventDAO dao = context.getBean(EventDAO.class);
		
		// get the user's events
		User me = new User();
		String myName = SecurityContextHolder.getContext().getAuthentication().getName();
		me.setUsername(myName);
		List<EventAttendee> myEventAttendees = dao.getEventsByUser(me);
		List<Event> myEvents = new ArrayList<>();
		for (EventAttendee e : myEventAttendees) {
			myEvents.add( dao.getEventByID( e.getEventAttendee().getEventID() ) );
		}
		Collections.sort(myEvents);
		
		// fill in the @Transient 'numberAttending' field for each Event
		for (Event e : myEvents) {
			e.setNumberAttending( dao.getNumberAttending( e.getEventID() ) );
		}
		
		// Pupulate variables stored in model
		model.addAttribute("myEvents", myEvents);				// all events listed in table
		model.addAttribute("eventDetails", new Event());		// use with details form
		model.addAttribute("eventToLeave", new Event());		// use with withdrawal form
		model.addAttribute("now", LocalDateTime.now());			// won't work in JSP
		
		context.close();
		return "PersonalEvents";
	}
}
