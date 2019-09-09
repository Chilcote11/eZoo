package com.examples.ezoo.controllers;

import javax.validation.Valid;

import org.apache.logging.log4j.Level;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.examples.ezoo.config.Config;
import com.examples.ezoo.dao.UserDAO;
import com.examples.ezoo.logger.Origin;
import com.examples.ezoo.logger.ZooLogger;
import com.examples.ezoo.model.User;

/**
 * Allows users to register new accounts
 * 
 * @author Cory Chilcote
 *
 */
@Controller
public class RegistrationController {
	
	private ZooLogger Log = new ZooLogger();

	/**
	 * Prepares attributes for registration page
	 * 
	 * @param model
	 * @return view name
	 */
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String displayRegistrationForm(Model model) {
		
		Log.controllerLog(Origin.CONTROLLER_REGISTRATION_GET, Level.INFO, "navigation");
		
		model.addAttribute("newUser", new User());
		return "register";
	}
	
	/**
	 * Validates form inputs and calls DAO method to save the new account to the database
	 * 
	 * @param model
	 * @param newUser
	 * @param errors
	 * @return view name
	 */
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String register(Model model, @Valid @ModelAttribute("newUser") User newUser, Errors errors) {
		
		Log.controllerLog(Origin.CONTROLLER_REGISTRATION_POST, Level.INFO, "navigation");
		
		if (errors.hasErrors()) {
			
			Log.controllerLog(Origin.CONTROLLER_REGISTRATION_POST, Level.WARN, "validation errors");
			
			model.addAttribute("message",  "Missing or invalid entries! Please try again");
			model.addAttribute("messageClass",  "alert-danger");
			
			return "register";
		}
		
		// Call DAO method
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		UserDAO dao = context.getBean(UserDAO.class);
		
		try {
			
			dao.saveUser(newUser);
			
			// leaving these out because of trouble with login page being heavily managed by Spring Security
//			model.addAttribute("message",  "User successfully created");
//			model.addAttribute("messageClass", "alert-success");
			
			context.close();			
			Log.controllerLog(Origin.CONTROLLER_REGISTRATION_POST, Level.INFO, "save successful");			
			return "redirect:/login";
			
		} catch(DataIntegrityViolationException e) {
			
			e.printStackTrace();			
			model.addAttribute("message",  newUser.getUsername() + " already exists");
			model.addAttribute("messageClass",  "alert-danger");			
			context.close();
			Log.controllerLog(Origin.CONTROLLER_REGISTRATION_POST, Level.ERROR, "exception: duplicate username");
			return "register";
			
		} catch (Exception e) {
			
			e.printStackTrace();			
			model.addAttribute("message",  "There was a problem creating the feeding schedule at this time");
			model.addAttribute("messageClass",  "alert-danger");			
			context.close();			
			Log.controllerLog(Origin.CONTROLLER_REGISTRATION_POST, Level.ERROR, "unknown exception thrown");			
			return "register";
			
		}
	}
}
