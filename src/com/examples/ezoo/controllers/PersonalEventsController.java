package com.examples.ezoo.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
import com.examples.ezoo.logger.ZooLogger;
import com.examples.ezoo.model.Event;
import com.examples.ezoo.model.EventAttendee;
import com.examples.ezoo.model.User;

@Controller
public class PersonalEventsController {

	private ZooLogger Log = new ZooLogger();
	
	@RequestMapping(value="/PersonalEvents", method=RequestMethod.GET)
	public String DisplayCalendar(Model model
			, @ModelAttribute("message") String message
			, @ModelAttribute("messageClass") String messageClass) {
		
		// TODO logging
		
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
		
		// Pupulate variables stored in model
		model.addAttribute("myEvents", myEvents);
		model.addAttribute("eventToLeave", new Event());
		
		context.close();
		return "PersonalEvents";
	}
}
