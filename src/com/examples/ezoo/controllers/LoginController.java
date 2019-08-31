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

@Controller
public class LoginController {
	
	private ZooLogger Log = new ZooLogger();
	
//	@RequestMapping(value= {"/login" 
//			, "/login?message=User+successfully+created&messageClass=alert-success"}
//			, method=RequestMethod.GET)
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String displayLoginForm(Model model
//			, @RequestParam(value="message", required=false) String message
//			, @RequestParam(value="messageClass", required=false) String messageClass
			, @RequestParam(value="error", required=false) String error
			, @ModelAttribute("message") String mssg
			, @ModelAttribute("messageClass") String mssgClass) {
		
		if (error != null) {
			Log.controllerLog(Origin.CONTROLLER_LOGINERROR_GET, Level.INFO, "navigation");
			model.addAttribute("message",  "Username or password is incorrect!");
			model.addAttribute("messageClass",  "alert-danger");
		}
//		else if (message != null) {
//			Log.controllerLog(Origin.CONTROLLER_LOGIN_GET, Level.INFO, "navigation");
//			model.addAttribute("message",  message);
//			model.addAttribute("messageClass",  messageClass);
//		}
//		else 
			Log.controllerLog(Origin.CONTROLLER_LOGIN_GET, Level.INFO, "navigation");
		
		model.addAttribute("userToValidate", new User());
		return "login";
	}
	
//	@RequestMapping(value="/login?error", method=RequestMethod.GET)					// nearly identical to login controller 
//	public String displayLoginErrorForm(Model model
//			, @ModelAttribute("message") String message
//			, @ModelAttribute("messageClass") String messageClass) {
//		
//		Log.controllerLog(Origin.CONTROLLER_LOGINERROR_GET, Level.INFO, "navigation");		// different origin
//		
//		model.addAttribute("userToValidate", new User());
//		return "redirect:/login";													// only real difference
//	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(Model model, @Valid @ModelAttribute("userToValidate") User newUser, Errors errors) {
		
		Log.controllerLog(Origin.CONTROLLER_LOGIN_POST, Level.INFO, "navigation");
		
		if (errors.hasErrors()) {
			Log.controllerLog(Origin.CONTROLLER_LOGIN_POST, Level.WARN, "validation errors");
			
			model.addAttribute("message",  "Missing or invalid entries! Please try again");
			model.addAttribute("messageClass",  "alert-danger");
			
			return "login";
		}
		
		model.addAttribute("authUser", newUser);		// for display in header
		
		return "redirect:/";		// makes no difference, set by defaultSuccessUrl("/")
	}
}
