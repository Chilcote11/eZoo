package com.examples.ezoo.controllers;

import java.sql.SQLIntegrityConstraintViolationException;

import javax.validation.Valid;

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
import com.examples.ezoo.model.Animal;

@Controller
public class AddAnimalController {
	
	@RequestMapping(value="/addAnimal", method=RequestMethod.GET)
	public String DisplayAddAnimalForm(Model model
			, @ModelAttribute("message") String message
			, @ModelAttribute("messageClass") String messageClass) {
		
		model.addAttribute("newAnimal", new Animal());
		return "addAnimal";		
	}
	
	@RequestMapping(value="/addAnimal", method=RequestMethod.POST)
	public String addAnimal(Model model, @Valid @ModelAttribute("newAnimal") Animal newAnimal, Errors errors) {
		
		if (errors.hasErrors()) {
			return "addAnimal";
		}

		
		//Call DAO method
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		AnimalDAO dao = context.getBean(AnimalDAO.class);

		try {
			dao.saveAnimal(newAnimal);

			model.addAttribute("message", "Animal successfully created");
			model.addAttribute("messageClass", "alert-success");
			context.close();
			return "redirect:/animalCare";
		}catch(DataIntegrityViolationException e){
			e.printStackTrace();			
			model.addAttribute("message", "Id of " + newAnimal.getAnimalID() + " is already in use");
			model.addAttribute("messageClass", "alert-danger");
			context.close();
			return "addAnimal";
		}catch (Exception e){
			e.printStackTrace();
			model.addAttribute("message", "There was a problem creating the animal at this time");
			model.addAttribute("messageClass", "alert-danger");
			context.close();
			return "addAnimal";
		}

	}
}
