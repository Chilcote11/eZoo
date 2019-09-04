package com.examples.ezoo.controllers;

import org.apache.logging.log4j.Level;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
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

@Controller
public class EventDeleteController {

	private ZooLogger Log = new ZooLogger();

	@RequestMapping(value="/EventDelete", method=RequestMethod.POST)
	public String deleteEvent(Model model, @ModelAttribute("eventToDelete") Event eventToDelete) {
		
		// TODO logging
		
		// Call DAO method
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		EventDAO dao = context.getBean(EventDAO.class);
		
		try {
			dao.deleteEvent(eventToDelete);
			model.addAttribute("message",  "Event successfully deleted");
			model.addAttribute("messageClass", "alert-success");
			context.close();
			// TODO logging
			return "redirect:/calendar";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message",  "There was a problem deleting the event at this time");
			model.addAttribute("messageClass",  "alert-danger");
			context.close();
			// TODO logging
			return "redirect:/calendar";
		}
	}
}
