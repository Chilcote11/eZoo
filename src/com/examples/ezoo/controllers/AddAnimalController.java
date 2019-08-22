package com.examples.ezoo.controllers;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.examples.ezoo.config.Config;
import com.examples.ezoo.dao.AnimalDAO;
import com.examples.ezoo.model.Animal;

@Controller
public class AddAnimalController {
	
	@RequestMapping(value="/addAnimal", method=RequestMethod.GET)
	public String DisplayAddAnimalForm(Model model
			, @ModelAttribute("message") String message
			, @ModelAttribute("messageClass") String messageClass) {
		
		// not setting in new model for now
//		model.addAttribute("message", message);
//		model.addAttribute("messageClass", messageClass);
		
		model.addAttribute("newAnimal", new Animal());
		return "addAnimal";		
	}
	
	@RequestMapping(value="/addAnimal", method=RequestMethod.POST)
	public String addAnimal(Model model,/*@Valid*/ @ModelAttribute("newAnimal") Animal newAnimal, /*, Errors errors*/
			@ModelAttribute("message") String message, 
			@ModelAttribute("messageClass") String messageClass) {
		
		// used to handle validation later on
		/*if (errors.hasErrors())
			return "addAnimal";
		else {
			// save animal logic below goes here
			return "animalCare";
		}*/
		
		//Call DAO method
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		AnimalDAO dao = context.getBean(AnimalDAO.class);
//		context.close();
		// TODO: deal with the message attribute commented out below
		try {
			dao.saveAnimal(newAnimal);
			context.close();
//			request.getSession().setAttribute("message", "Animal successfully created");
//			request.getSession().setAttribute("messageClass", "alert-success");
			model.addAttribute("message", "Animal successfully created");
			model.addAttribute("messageClass", "alert-success");
			return "animalCare";
		}catch(SQLIntegrityConstraintViolationException e){
			e.printStackTrace();			
			//change the message
//			request.getSession().setAttribute("message", "Id of " + animalToSave.getAnimalID() + " is already in use");
//			request.getSession().setAttribute("messageClass", "alert-danger");
			model.addAttribute("message", "Id of " + newAnimal.getAnimalID() + " is already in use");
			model.addAttribute("messageClass", "alert-danger");
			// redirect
			context.close();
			return "addAnimal";
		}catch (Exception e){
			e.printStackTrace();
			//change the message
//			request.getSession().setAttribute("message", "There was a problem creating the animal at this time");
//			request.getSession().setAttribute("messageClass", "alert-danger");
			model.addAttribute("message", "There was a problem creating the animal at this time");
			model.addAttribute("messageClass", "alert-danger");
			// redirect
			context.close();
			return "addAnimal";
		}
	}
}
