package com.examples.ezoo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.examples.ezoo.logger.ZooLogger;

@Controller
public class HomeController {

	private ZooLogger Log = new ZooLogger();
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String displayHomePage() {
		return "index";
	}
}
