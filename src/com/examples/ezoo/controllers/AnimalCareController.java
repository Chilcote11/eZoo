package com.examples.ezoo.controllers;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.apache.logging.log4j.Level;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.examples.ezoo.config.Config;
import com.examples.ezoo.dao.AnimalDAO;
import com.examples.ezoo.logger.Origin;
import com.examples.ezoo.logger.ZooLogger;
import com.examples.ezoo.model.Animal;

@Controller
public class AnimalCareController {
	
	private ZooLogger Log = new ZooLogger();

	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value="/animalCare", method=RequestMethod.GET)
	public String DisplayAnimalCare(Model model) {
		
		Log.controllerLog(Origin.CONTROLLER_ANIMALCARE_GET, Level.INFO, "navigation");
		
		// Grab a list of Animals from the Database
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		AnimalDAO dao = context.getBean(AnimalDAO.class);
		List<Animal> animals = dao.getAllAnimals();

		// Populate the list into a variable that will be stored in the model object
		model.addAttribute("animals", animals);
		
		Animal largest = new Animal();
		for (Animal a : animals)
			if (a.getWeight() > largest.getWeight())
				largest = a;
		model.addAttribute("largestAnimal", largest);
		
		Animal longest = new Animal();
		for (Animal a : animals)
			if (a.getName().length() > longest.getName().length())
				longest = a;
		model.addAttribute("longestNamedAnimal", longest);
		
		model.addAttribute("animal", new Animal());			// needed for animal care page
				// used for deletion, assignment, and unassignment forms
		
		context.close();
		return "animalCare";
	}
}
