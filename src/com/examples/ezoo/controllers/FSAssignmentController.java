package com.examples.ezoo.controllers;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	Animal animalToAssign; 	// set in get method, used in POST method
							// .. since I'm having so much trouble passing it into POST method
							// this may not be best practice though. unsure what rules are

	@RequestMapping(value="/FSAssignment", method=RequestMethod.GET)
	public String DisplayAssignmentOptions(Model model, @ModelAttribute("animal") Animal selectedAnimal) {
//			, @ModelAttribute("message") String message
//			, @ModelAttribute("messageClass") String messageClass) {
		
		// clear in new model
		model.addAttribute("message", null);
		model.addAttribute("messageClass", null);
		
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		FeedingScheduleDAO dao = context.getBean(FeedingScheduleDAO.class);
		AnimalDAO animalDAO = context.getBean(AnimalDAO.class);
		List<FeedingSchedule> feedingSchedules = dao.getAllFeedingSchedules();
		
		// Populate `animals` field of each feeding schedule
		List<Animal> animals = animalDAO.getAllAnimals();
		Collections.sort(feedingSchedules);			// sort. This is unnecessary. Line below is not
		Collections.sort(animals);
		for (FeedingSchedule schedule : feedingSchedules) {
			String animalsWithSchedule = "";
			int count = 0;
			for (Animal a : animals) {
				if (a.getFeedingScheduleID() != null) {
					if (schedule.getScheduleID() == a.getFeedingScheduleID()) {
						count++;
						String comma = "";
						if (count > 1) {
							comma = ", ";
						}
						animalsWithSchedule += comma + a.getName() + 
								"[" + a.getAnimalID() + "]";	
					}
				}
			}
			schedule.setAnimals(animalsWithSchedule); // remember, this purposefully never makes it to database
		}
		
		model.addAttribute("scheduleToAssign", new FeedingSchedule());
		model.addAttribute("feedingSchedules", feedingSchedules);
		
//		model.addAttribute("animal", animal);
		animalToAssign = selectedAnimal;
		
		
		context.close();
		return "assignFeedingSchedule";
	}
	
	@RequestMapping(value="/FSAssignment", method=RequestMethod.POST)
	public String assignFeedingSchedule(Model model, @ModelAttribute("scheduleToAssign") FeedingSchedule fs) {
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
			for (Animal a : animals) {		// need to fill in the gaps, animalToAssign only has ID populated
				if (a.getAnimalID() == animalToAssign.getAnimalID()) {
					animal = a;
				}
			}
			
			// unassignment logic
			if (animal.getFeedingScheduleID() != null && animal.getFeedingScheduleID() > 0) { // not null and not zero
				FSDAO.removeFeedingSchedule(animal);
				model.addAttribute("message",  "Feeding schedule successfully removed");
			}
			// assignment logic
			else {
				FSDAO.assignFeedingSchedule(fs, animal);
				
				model.addAttribute("message",  "Feeding schedule successfully assigned");
			}
			
			model.addAttribute("messageClass", "alert-success");
			context.close();
			return "animalCare";
		} catch (Exception e) {
			e.printStackTrace();
			// change the message
			model.addAttribute("message",  "There was a problem assigning or unassigning the feeding schedule at this time");
			model.addAttribute("messageClass",  "alert-danger");
			context.close();
			return "assignFeedingSchedule";
		}
		
	}
}
