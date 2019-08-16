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
import com.examples.ezoo.dao.DAOUtilities;
import com.examples.ezoo.model.Animal;

@Controller
public class AddAnimalController {
	
	@RequestMapping(value="/addAnimal", method=RequestMethod.GET)
	public String DisplayAddAnimalForm(Model model) {
		model.addAttribute("newAnimal", new Animal());
		return "addAnimal";		
	}
	
	@RequestMapping(value="/addAnimal", method=RequestMethod.POST)
	public String addAnimal(/*@Valid*/ @ModelAttribute("newAnimal") Animal newAnimal/*, Errors errors*/) {
		
		// used to handle validation later on
		/*if (errors.hasErrors())
			return "addAnimal";
		else {
			// save animal logic below goes here
			return "animalCare";
		}*/
		
		//Get Parameters
		long id = newAnimal.getAnimalID();
		String name = newAnimal.getName();
		String kingdom = newAnimal.getTaxKingdom();
		String phylum = newAnimal.getTaxPhylum();
		String clazz = newAnimal.getTaxClass();
		String order = newAnimal.getTaxOrder();
		String family = newAnimal.getTaxFamily();
		String genus = newAnimal.getTaxGenus();
		String species = newAnimal.getTaxSpecies();
		String type = newAnimal.getType();
		String healthStatus = newAnimal.getHealthStatus();
		double height = newAnimal.getHeight();
		double weight = newAnimal.getWeight();
		int feedingScheduleID = newAnimal.getFeedingScheduleID();
		
		//Create an Animal object from the parameters
		Animal animalToSave = new Animal(
				id, 
				name, 
				kingdom,
				phylum,
				clazz,
				order,
				family,
				genus,
				species,
				height,
				weight,
				type,
				healthStatus, 
				feedingScheduleID);
		
		//Call DAO method
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		AnimalDAO dao = context.getBean(AnimalDAO.class);
		context.close();
		// TODO: deal with the message attribute commented out below
		try {
			dao.saveAnimal(animalToSave);
//			request.getSession().setAttribute("message", "Animal successfully created");
//			request.getSession().setAttribute("messageClass", "alert-success");
			return "animalCare";
		}catch(SQLIntegrityConstraintViolationException e){
			e.printStackTrace();			
			//change the message
//			request.getSession().setAttribute("message", "Id of " + animalToSave.getAnimalID() + " is already in use");
//			request.getSession().setAttribute("messageClass", "alert-danger");
			// redirect
			return "addAnimal";
		}catch (Exception e){
			e.printStackTrace();
			//change the message
//			request.getSession().setAttribute("message", "There was a problem creating the animal at this time");
//			request.getSession().setAttribute("messageClass", "alert-danger");
			// redirect
			return "addAnimal";
		}
	}
}
