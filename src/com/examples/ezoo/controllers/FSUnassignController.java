package com.examples.ezoo.controllers;

import java.util.Collections;
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
import com.examples.ezoo.dao.FeedingScheduleDAO;
import com.examples.ezoo.model.Animal;

@Controller
public class FSUnassignController {

	@RequestMapping(value="/FSUnassign", method=RequestMethod.POST)
	public String unassignFeedingSchedule(Model model, @ModelAttribute("animal") Animal animalToUnassign) {
//			, @ModelAttribute("message") String message
//			, @ModelAttribute("messageClass") String messageClass) {
		
		
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		AnimalDAO animalDAO = context.getBean(AnimalDAO.class);
		FeedingScheduleDAO FSDAO = context.getBean(FeedingScheduleDAO.class);
		
		try {
			// animalDAO has no "getAnimalByID" method, so here's a work-around
			// could choose to go add one later, wouldn't be too hard
			List<Animal> animals = animalDAO.getAllAnimals();
			Collections.sort(animals);		// unnecessary, but I like it
			Animal animal = new Animal();
			for (Animal a : animals) {		// need to fill in the gaps, animalToUnassign only has ID populated
				if (a.getAnimalID() == animalToUnassign.getAnimalID()) {
					animal = a;
				}
			}
			
			// unassignment logic
			FSDAO.removeFeedingSchedule(animal);
			model.addAttribute("message",  "Feeding schedule successfully removed");			
			model.addAttribute("messageClass", "alert-success");
			context.close();
			return "redirect:/animalCare";
		} catch (Exception e) {
			e.printStackTrace();
			// change the message
			model.addAttribute("message",  "There was a problem unassigning the feeding schedule at this time");
			model.addAttribute("messageClass",  "alert-danger");
			context.close();
			return "redirect:/animalCare";
		}
		
	}
}
