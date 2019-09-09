package com.examples.ezoo.controllers;

import java.time.LocalDateTime;
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
 * Allows users and employees to see and interact with the zoo's events
 * 
 * @author Cory Chilcote
 * @since 2.0
 *
 */
@Controller
public class CalendarController {

	private ZooLogger Log = new ZooLogger();
	
	/**
	 * Prepares attributes for event management (calendar) page
	 * 
	 * @param model - functions like a map with String keys and values
	 * 		+ similar to the request and session objects in a servlet.
	 * 			model contents are implicitly added to the Request object for JSPs.
	 * 			You can retrieve model data easily with JSTL.
	 * @return view name
	 *
	 */
	@RequestMapping(value="/calendar", method=RequestMethod.GET)
	public String DisplayCalendar(Model model) {
		
		Log.controllerLog(Origin.CONTROLLER_CALENDAR_GET, Level.INFO, "navigation");
		
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		EventDAO dao = context.getBean(EventDAO.class);
		List<Event> events = dao.getAllEvents();
		Collections.sort(events);
		
		// fill in the @Transient 'numberAttending' field for each Event
		for (Event e : events) {
			e.setNumberAttending( dao.getNumberAttending( e.getEventID() ) );
		}
		
		// Pupulate variables stored in model
		model.addAttribute("events", events);
		model.addAttribute("eventToDelete", new Event());
		model.addAttribute("eventToUpdate", new Event());
		model.addAttribute("eventDetails", new Event());		// use with details form
		model.addAttribute("eventToAttend", new Event());
		model.addAttribute("eventToLeave", new Event());
		model.addAttribute("now", LocalDateTime.now());			// won't work in JSP, must have here
		
		// add a list of this users events to the model object
		User me = new User();
		String myName = SecurityContextHolder.getContext().getAuthentication().getName();
		me.setUsername(myName);
		List<EventAttendee> myEvents = dao.getEventsByUser(me);
		model.addAttribute("myEvents", myEvents);
		
		context.close();
		return "calendar";
	}
}
