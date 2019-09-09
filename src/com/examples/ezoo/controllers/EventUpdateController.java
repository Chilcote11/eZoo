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

/** 
 * Allows users to update a previously created event
 * 
 * @author Cory Chilcote
 * @since 2.0
 */
@Controller
public class EventUpdateController {

	private ZooLogger Log = new ZooLogger();
	
	/**
	 * Prepares attributes for event update page
	 * 
	 * @param model - functions like a map with String keys and values
	 * 		+ similar to the request and session objects in a servlet.
	 * 			model contents are implicitly added to the Request object for JSPs.
	 * 			You can retrieve model data easily with JSTL.
	 * @param eventToUpdate
	 * @return view name
	 */
	@RequestMapping(value="/EventUpdate", method=RequestMethod.GET)
	public String DisplayUpdatePage(Model model, @ModelAttribute("eventToUpdate") Event eventToUpdate) {
		
		Log.controllerLog(Origin.CONTROLLER_EVENTUPDATE_GET, Level.INFO, "navigation");
		
		model.addAttribute("eventToUpdate", eventToUpdate);	// transfer
		model.addAttribute("creator", SecurityContextHolder.getContext().getAuthentication().getName());
		return "EventUpdate";
	}
	
	/**
	 * Validates form inputs and calls DAO method to update an event
	 * 
	 * @param model - functions like a map with String keys and values
	 * 		+ similar to the request and session objects in a servlet.
	 * 			model contents are implicitly added to the Request object for JSPs.
	 * 			You can retrieve model data easily with JSTL.
	 * @param eventToUpdate
	 * @param errors - validation errors. reference model class (entity)
	 * @return view name
	 */
	@RequestMapping(value="/EventUpdate", method=RequestMethod.POST)
	public String updateEvent(Model model, @Valid @ModelAttribute("eventToUpdate") Event eventToUpdate, Errors errors) {
		
		Log.controllerLog(Origin.CONTROLLER_EVENTUPDATE_POST, Level.INFO, "navigation");
		
		if (errors.hasErrors()) {
			Log.controllerLog(Origin.CONTROLLER_EVENTUPDATE_POST, Level.WARN, "validation errors");
			
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
			Log.controllerLog(Origin.CONTROLLER_EVENTUPDATE_POST, Level.INFO, "update successful");
			return "redirect:/calendar";
		} catch (Exception e) {
			e.printStackTrace();;
			model.addAttribute("message",  "There was a problem updating the event at this time");
			model.addAttribute("messageClass",  "alert-danger");
			context.close();
			Log.controllerLog(Origin.CONTROLLER_EVENTUPDATE_POST, Level.ERROR, "unknown exception thrown");
			return "EventUpdate";
		}
	}
}
