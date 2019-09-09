package com.examples.ezoo.controllers;

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
import com.examples.ezoo.model.User;

/** 
 * Allow users to leave an event they've previously signed up for
 * 
 * @author Cory Chilcote
 *
 */
@Controller
public class EventLeaveController {

	private ZooLogger Log = new ZooLogger();
	
	/**
	 * Calls DAO method to delete an EventAttendee
	 * 
	 * @param model
	 * @param eventToLeave
	 * @return view name
	 */
	@RequestMapping(value="/EventLeave", method=RequestMethod.POST)
	public String signUp(Model model, @ModelAttribute("eventToLeave") Event eventToLeave) {

		Log.controllerLog(Origin.CONTROLLER_EVENTLEAVE_POST, Level.INFO, "navigation");
		
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		EventDAO dao = context.getBean(EventDAO.class);
		try {
			User me = new User();
			String myName = SecurityContextHolder.getContext().getAuthentication().getName();
			me.setUsername(myName);
			dao.leaveEvent(me, eventToLeave);
			model.addAttribute("message",  "You've successfully cancelled your attendance");
			model.addAttribute("messageClass", "alert-success");
			context.close();
			Log.controllerLog(Origin.CONTROLLER_EVENTLEAVE_POST, Level.INFO, "leave successful");
			return "redirect:/calendar";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message",  "There was a problem leaving the event at this time");
			model.addAttribute("messageClass",  "alert-danger");
			context.close();
			Log.controllerLog(Origin.CONTROLLER_EVENTLEAVE_POST, Level.ERROR, "unknown exception thrown");
			return "redirect:/calendar";
		}
	}
}
