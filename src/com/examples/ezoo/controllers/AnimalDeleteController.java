package com.examples.ezoo.controllers;

import javax.annotation.security.DenyAll;
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
//@DenyAll
public class AnimalDeleteController {
	
	private ZooLogger Log = new ZooLogger();

	@RolesAllowed("ROLE_ADMIN")
//	@DenyAll
	@RequestMapping(value="/AnimalDelete", method=RequestMethod.POST)
	public String deleteAnimal(Model model, @ModelAttribute("animal") Animal animalToDelete) {
		
		Log.controllerLog(Origin.CONTROLLER_DELETEANIMAL_POST, Level.INFO, "navigation");
		
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		AnimalDAO dao = context.getBean(AnimalDAO.class);
		
		try {
			dao.deleteAnimal(animalToDelete);;
			model.addAttribute("message",  "Animal successfully deleted");
			model.addAttribute("messageClass", "alert-success");
			context.close();
			Log.controllerLog(Origin.CONTROLLER_DELETEANIMAL_POST, Level.INFO, "deletion successful");
			return "redirect:/animalCare";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message",  "There was a problem deleting the animal at this time");
			model.addAttribute("messageClass",  "alert-danger");
			context.close();
			Log.controllerLog(Origin.CONTROLLER_DELETEANIMAL_POST, Level.ERROR, "unknown exception thrown");
			return "redirect:/animalCare";
		}
	}
}
