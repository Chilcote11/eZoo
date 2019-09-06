package com.examples.ezoo.controllers;

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
import com.examples.ezoo.model.User;

@Controller
public class EventSignUpController {

	private ZooLogger Log = new ZooLogger();
	
	@RequestMapping(value="/EventSignUp", method=RequestMethod.POST)
	public String signUp(Model model, @ModelAttribute("eventToAttend") Event eventToAttend) {

		// TODO logging
		
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		EventDAO dao = context.getBean(EventDAO.class);
		try {
			User me = new User();
			String myName = SecurityContextHolder.getContext().getAuthentication().getName();
			me.setUsername(myName);
			dao.signUpForEvent(me, eventToAttend);
			model.addAttribute("message",  "Successful signup!");
			model.addAttribute("messageClass", "alert-success");
			context.close();
			// TODO logging
			return "redirect:/calendar";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message",  "There was a problem signing up for the event at this time");
			model.addAttribute("messageClass",  "alert-danger");
			context.close();
			// TODO logging
			return "redirect:/calendar";
		}
	}
}
