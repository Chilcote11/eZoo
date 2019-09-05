package com.examples.ezoo.controllers;

import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.examples.ezoo.config.Config;
import com.examples.ezoo.dao.EventDAO;
import com.examples.ezoo.logger.ZooLogger;
import com.examples.ezoo.model.Event;

@Controller
public class CalendarController {

	private ZooLogger Log = new ZooLogger();
	
	@RequestMapping(value="/calendar", method=RequestMethod.GET)
	public String DisplayCalendar(Model model
			, @ModelAttribute("message") String message
			, @ModelAttribute("messageClass") String messageClass) {
		
		// TODO logging
		
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		EventDAO dao = context.getBean(EventDAO.class);
		List<Event> events = dao.getAllEvents();
		Collections.sort(events);
		
		// Pupulate variables stored in model
		model.addAttribute("events", events);
		model.addAttribute("eventToDelete", new Event());
		model.addAttribute("eventToUpdate", new Event());
		model.addAttribute("eventToAttend", new Event());
		
		
		
		context.close();
		return "calendar";
	}
}
