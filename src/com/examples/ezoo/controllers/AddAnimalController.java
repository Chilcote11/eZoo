package com.examples.ezoo.controllers;

import java.sql.SQLIntegrityConstraintViolationException;

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
import com.examples.ezoo.dao.AnimalDAO;
import com.examples.ezoo.logger.Origin;
import com.examples.ezoo.logger.ZooLogger;
import com.examples.ezoo.model.Animal;

@Controller
public class AddAnimalController {
	
	private ZooLogger Log = new ZooLogger();
	
	@RequestMapping(value="/AnimalAdd", method=RequestMethod.GET)
	public String DisplayAddAnimalForm(Model model
			, @ModelAttribute("message") String message
			, @ModelAttribute("messageClass") String messageClass) {
		
		Log.controllerLog(Origin.CONTROLLER_ADDANIMAL_GET, Level.INFO, "navigation");
		
		model.addAttribute("newAnimal", new Animal());
		
		return "AnimalAdd";		
	}
	
	@RequestMapping(value="/AnimalAdd", method=RequestMethod.POST)
	public String addAnimal(Model model, @Valid @ModelAttribute("newAnimal") Animal newAnimal, Errors errors) {
		
		Log.controllerLog(Origin.CONTROLLER_ADDANIMAL_POST, Level.INFO, "navigation");
		
		if (errors.hasErrors()) {
			Log.controllerLog(Origin.CONTROLLER_ADDANIMAL_POST, Level.WARN, "validation errors");
			
			model.addAttribute("message",  "Missing or invalid entries! Please try again");
			model.addAttribute("messageClass",  "alert-danger");
			
			return "AnimalAdd";
		}

		
		//Call DAO method
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		AnimalDAO dao = context.getBean(AnimalDAO.class);

		try {
			dao.saveAnimal(newAnimal);

			model.addAttribute("message", "Animal successfully created");
			model.addAttribute("messageClass", "alert-success");
			context.close();
			Log.controllerLog(Origin.CONTROLLER_ADDANIMAL_POST, Level.INFO, "save successful");
			return "redirect:/animalCare";
		}catch(DataIntegrityViolationException e){
			e.printStackTrace();			
			model.addAttribute("message", "Id of " + newAnimal.getAnimalID() + " is already in use");
			model.addAttribute("messageClass", "alert-danger");
			context.close();
			Log.controllerLog(Origin.CONTROLLER_ADDANIMAL_POST, Level.ERROR, "exception: duplicate animalID");
			return "AnimalAdd";
		}catch (Exception e){
			e.printStackTrace();
			model.addAttribute("message", "There was a problem creating the animal at this time");
			model.addAttribute("messageClass", "alert-danger");
			context.close();
			Log.controllerLog(Origin.CONTROLLER_ADDANIMAL_POST, Level.ERROR, "unknown exception thrown");
			return "AnimalAdd";
		}

	}
}
