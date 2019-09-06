package com.examples.ezoo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.examples.ezoo.logger.ZooLogger;
import com.examples.ezoo.model.Event;

@Controller
public class EventDetailsController {

private ZooLogger Log = new ZooLogger();
	
	@RequestMapping(value="/EventDetails", method=RequestMethod.GET)
	public String DisplayCalendar(Model model
			, @ModelAttribute("eventToDisplay") Event eventToDisplay
			, @ModelAttribute("message") String message
			, @ModelAttribute("messageClass") String messageClass) {
		
		// TODO logging
		
		model.addAttribute("eventToDisplay", eventToDisplay);
		
		return "EventDetails";
	}
}
