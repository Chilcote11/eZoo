package com.examples.ezoo.controllers;

import org.apache.logging.log4j.Level;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.examples.ezoo.logger.Origin;
import com.examples.ezoo.logger.ZooLogger;

/**
 * Allows me to store index.jsp in WEB-INF/views with all the other JSPs.
 * 
 * Without this it must be outside the WEB-INF folder in WebContent, 
 * which breaks convention
 * Also allows for logging
 * 
 * @author Cory Chilcote
 *
 */
@Controller
public class HomeController {

	private ZooLogger Log = new ZooLogger();
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String displayHomePage() {
		
		Log.controllerLog(Origin.CONTROLLER_HOME_GET, Level.INFO, "navigation");
		
		return "index";
	}
}
