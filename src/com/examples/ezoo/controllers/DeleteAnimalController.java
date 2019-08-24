package com.examples.ezoo.controllers;

import java.util.List;

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
public class DeleteAnimalController {

	@RequestMapping(value="/deleteAnimal", method=RequestMethod.POST)
	public String deleteAnimal(Model model, @ModelAttribute("animal") Animal animalToDelete) {
		
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		AnimalDAO dao = context.getBean(AnimalDAO.class);
		
		try {
			dao.deleteAnimal(animalToDelete);;
			model.addAttribute("message",  "Animal successfully deleted");
			model.addAttribute("messageClass", "alert-success");
			context.close();
			return "redirect:/animalCare";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message",  "There was a problem deleting the animal at this time");
			model.addAttribute("messageClass",  "alert-danger");
			context.close();
			return "redirect:/animalCare";
		}
	}
}
