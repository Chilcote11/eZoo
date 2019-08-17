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
import com.examples.ezoo.model.FeedingSchedule;

/**
 * Controller implementation class FSAssignmentController
 * 		calls the DAO method to assign/remove a feeding schedule from a given animal
 * 		"unassignment" logic (post method only) accessed from animalCare page
 * 		"assignment" logic (uses both get and post) accessed from animalCare page
 * 			but then redirects to (gets) assignFeedingSchedule Controller.  from there
 * 			a feeding schedule is chosen to assign (post)
 * 
 * Potential future update:
 * 		add a new dao method to get an animal by its unique ID (see lines 84, 85)
 */
@Controller
public class FSAssignmentController {

	@RequestMapping(value="/FSAssignment", method=RequestMethod.GET)
	public String DisplayAssignmentOptions(Model model, /*@Valid*/ @ModelAttribute("animalID") long animalID/*, Errors errors*/) {
		
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		FeedingScheduleDAO dao = context.getBean(FeedingScheduleDAO.class);
		AnimalDAO animalDAO = context.getBean(AnimalDAO.class);
		context.close();
		List<FeedingSchedule> feedingSchedules = dao.getAllFeedingSchedules();
		
		// Populate `animals` field of each feeding schedule
		List<Animal> animals = animalDAO.getAllAnimals();
		Collections.sort(feedingSchedules);			// sort. This is unnecessary. Line below is not
		Collections.sort(animals);
		for (FeedingSchedule schedule : feedingSchedules) {
			String animalsWithSchedule = "";
			int count = 0;
			for (Animal animal : animals) {
				if (schedule.getScheduleID() == animal.getFeedingScheduleID()) {
					count++;
					String comma = "";
					if (count > 1) {
						comma = ", ";
					}
					animalsWithSchedule += comma + animal.getName() + 
							"[" + animal.getAnimalID() + "]";	
				}
			}
			schedule.setAnimals(animalsWithSchedule); // remember, this purposefully never makes it to database
		}
		
		// Populate the list into a variable that will be stored in the model
		model.addAttribute("feedingSchedules", feedingSchedules);
		model.addAttribute("scheduleIDToAssign");
		model.addAttribute("feedingTimeToAssign");
		model.addAttribute("recurrenceToAssign");
		model.addAttribute("foodToAssign");
		model.addAttribute("notesToAssign");
		model.addAttribute("animalID", animalID);
		
		return "assingFeedingSchedule";
	}
	
	@RequestMapping(value="FSAssignment", method=RequestMethod.POST)
	public String assignFeedingSchedule(/*@Valid*/ @ModelAttribute("animalID") long animalID, @ModelAttribute("fs") FeedingSchedule fs/*, Errors errors*/) {
		
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		AnimalDAO animalDAO = context.getBean(AnimalDAO.class);
		FeedingScheduleDAO FSDAO = context.getBean(FeedingScheduleDAO.class);
		context.close();
		// TODO: deal with the message attribute commented out below
		
		try {
			// animalDAO has no "getAnimalByID" method, so here's a work-around
			// could choose to go add one later, wouldn't be too hard
			List<Animal> animals = animalDAO.getAllAnimals();
			Collections.sort(animals);		// unnecessary, but I like it
			Animal animal = new Animal();
			for (Animal a : animals) {
				if (a.getAnimalID() == animalID)
					animal = a;
			}
			
			// unassignment logic
			if (animal.getFeedingScheduleID() > 0) {
				FSDAO.removeFeedingSchedule(animal);
//				request.getSession().setAttribute("message",  "Feeding schedule successfully removed");
			}
			// assignment logic
			else {
				
				FSDAO.assignFeedingSchedule(fs, animal);
				
//				request.getSession().setAttribute("message",  "Feeding schedule successfully assigned");
			}
			
//			request.getSession().setAttribute("messageClass", "alert-success");
			return "animalCare";
		} catch (Exception e) {
			e.printStackTrace();
			// change the message
//			request.getSession().setAttribute("message",  "There was a problem assigning or unassigning the feeding schedule at this time");
//			request.getSession().setAttribute("messageClass",  "alert-danger");
			return "animalCare";
		}
		
	}
}
