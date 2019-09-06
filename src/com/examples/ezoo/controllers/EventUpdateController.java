package com.examples.ezoo.controllers;

import javax.validation.Valid;

import org.apache.logging.log4j.Level;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class EventUpdateController {

	private ZooLogger Log = new ZooLogger();
	
	@RequestMapping(value="/EventUpdate", method=RequestMethod.GET)
	public String DisplayUpdatePage(Model model, @ModelAttribute("eventToUpdate") Event eventToUpdate
			, @ModelAttribute("message") String message
			, @ModelAttribute("messageClass") String messageClass) {
		
		// TODO logging
		
		model.addAttribute("eventToUpdate", eventToUpdate);	// transfer
		model.addAttribute("creator", SecurityContextHolder.getContext().getAuthentication().getName()); // currently unused
		return "EventUpdate";
	}
	
	@RequestMapping(value="/EventUpdate", method=RequestMethod.POST)
	public String updateEvent(Model model, @Valid @ModelAttribute("eventToUpdate") Event eventToUpdate, Errors errors) {
		
		// TODO logging
		
		if (errors.hasErrors()) {
			// TODO logging
			
			model.addAttribute("message",  "Missing or invalid entries! Please try again");
			model.addAttribute("messageClass",  "alert-danger");
			
			return "EventUpdate";
		}
		
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		EventDAO dao = context.getBean(EventDAO.class);
		
		// Call DAO method
		try {
			dao.updateEvent(eventToUpdate);
			model.addAttribute("message",  "Event successfully updated");
			model.addAttribute("messageClass", "alert-success");
			context.close();
			// TODO logging
			return "redirect:/calendar";
		} catch (Exception e) {
			e.printStackTrace();;
			model.addAttribute("message",  "There was a problem updating the event at this time");
			model.addAttribute("messageClass",  "alert-danger");
			context.close();
			// TODO logging
			return "EventUpdate";
		}
	}
}
