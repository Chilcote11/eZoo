package com.examples.ezoo.controllers;

import javax.validation.Valid;

import org.apache.logging.log4j.Level;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.examples.ezoo.logger.Origin;
import com.examples.ezoo.logger.ZooLogger;
import com.examples.ezoo.model.User;

/** 
 * Allows users to login to their account. 
 * 
 * Note: login handled by Spring based on config files
 * 
 * @author Cory Chilcote
 *
 */
@Controller
public class LoginController {
	
	private ZooLogger Log = new ZooLogger();
	
	/**
	 * Prepares attributes for the login page
	 * 
	 * @param model
	 * @param error
	 * @return view name
	 */
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String displayLoginForm(Model model
			, @RequestParam(value="error", required=false) String error) {
		
		if (error != null) {
			Log.controllerLog(Origin.CONTROLLER_LOGINERROR_GET, Level.INFO, "navigation");
			model.addAttribute("message",  "Username or password is incorrect!");
			model.addAttribute("messageClass",  "alert-danger");
		}

		Log.controllerLog(Origin.CONTROLLER_LOGIN_GET, Level.INFO, "navigation");
		
		model.addAttribute("userToValidate", new User());
		return "login";
	}
	
	/**
	 * Validates a user's login credentials
	 * 
	 * Spring handles the heavy lifting here, based on the instructions
	 * I gave in WebSecurityConfiguration
	 * 
	 * @param model
	 * @param newUser
	 * @param errors
	 * @return view name
	 */
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(Model model, @Valid @ModelAttribute("userToValidate") User newUser, Errors errors) {
		
		Log.controllerLog(Origin.CONTROLLER_LOGIN_POST, Level.INFO, "navigation");
		
		if (errors.hasErrors()) {
			Log.controllerLog(Origin.CONTROLLER_LOGIN_POST, Level.WARN, "validation errors");
			
			model.addAttribute("message",  "Missing or invalid entries! Please try again");
			model.addAttribute("messageClass",  "alert-danger");
			
			return "login";
		}
			
		return "redirect:/";		// makes no difference, set by defaultSuccessUrl("/") in WebSecurityConfiguration
	}
}
