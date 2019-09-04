package com.examples.ezoo.controllers;

import javax.validation.Valid;

import org.apache.logging.log4j.Level;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.examples.ezoo.config.Config;
import com.examples.ezoo.dao.EventDAO;
import com.examples.ezoo.logger.Origin;
import com.examples.ezoo.logger.ZooLogger;
import com.examples.ezoo.model.Event;

@Controller
public class EventCreate {
	
	private ZooLogger Log = new ZooLogger();
	
	@RequestMapping(value="/EventCreate", method=RequestMethod.GET)
	public String DisplayCreateEventForm(Model model
			, @ModelAttribute("message") String message
			, @ModelAttribute("messageClass") String messageClass) {
		
		// TODO logging
		
		model.addAttribute("newEvent", new Event());
		return "EventCreate";
	}
	
	@RequestMapping(value="/EventCreate", method=RequestMethod.POST)
	public String createEvent(Model model, @Valid @ModelAttribute("newFeedingSchedule") Event newEvent, Errors errors) {
		
		// TODO logging
		
		if (errors.hasErrors()) {
			// TODO logging
			
			model.addAttribute("message",  "Missing or invalid entries! Please try again");
			model.addAttribute("messageClass",  "alert-danger");
			
			return "EventCreate";
		}
		
		// Call DAO method
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		EventDAO dao = context.getBean(EventDAO.class);
		
		try {
			dao.saveEvent(newEvent);
			context.close();
			model.addAttribute("message",  "Event successfully created");
			model.addAttribute("messageClass", "alert-success");
			// TODO logging
			return "redirect:/calendar";
		} catch(DataIntegrityViolationException e) {
			e.printStackTrace();
			model.addAttribute("message",  "Id of " + newEvent.getEventID() + " is already in use");
			model.addAttribute("messageClass",  "alert-danger");
			context.close();
			// TODO logging
			return "EventCreate";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message",  "There was a problem creating the event at this time");
			model.addAttribute("messageClass",  "alert-danger");
			context.close();
			// TODO logging
			return "EventCreate";
		}

	}

}
