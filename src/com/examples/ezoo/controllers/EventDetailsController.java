package com.examples.ezoo.controllers;

import org.apache.logging.log4j.Level;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.examples.ezoo.logger.Origin;
import com.examples.ezoo.logger.ZooLogger;
import com.examples.ezoo.model.Event;

/**
 * Allows users to check out the details of an event
 * 
 * @author Cory Chilcote
 *
 */
@Controller
public class EventDetailsController {

private ZooLogger Log = new ZooLogger();
	
	/**
	 * Prepares attributes for event details page
	 * 
	 * @param model
	 * @param eventToDisplay
	 * @return view name
	 */
	@RequestMapping(value="/EventDetails", method=RequestMethod.GET)
	public String DisplayEventDetails(Model model
			, @ModelAttribute("eventToDisplay") Event eventToDisplay) {
		
		Log.controllerLog(Origin.CONTROLLER_EVENTDETAILS_GET, Level.INFO, "navigation");
		
		model.addAttribute("eventToDisplay", eventToDisplay);
		
		return "EventDetails";
	}
}
