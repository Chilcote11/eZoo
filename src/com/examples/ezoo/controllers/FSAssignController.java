package com.examples.ezoo.controllers;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Level;
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
import com.examples.ezoo.logger.Origin;
import com.examples.ezoo.logger.ZooLogger;
import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.FeedingSchedule;

/**
 * Allows users to assign a feeding schedule to an animal
 * 
 * Potential future update:
 * 		add a new dao method to get an animal by its unique ID (see TODO)
 * 
 * @author Cory Chilcote
 * 
 */
@Controller
public class FSAssignController {
	
	private ZooLogger Log = new ZooLogger();
	
	Animal animalToAssign; 	// set in GET method, used in POST method
							// .. since I'm having so much trouble passing it into POST method
							// this may not be best practice though. unsure what rules are

	/**
	 * Prepares attributes for the page used to choose which feeding schedule to assign to the chosen animal
	 * 
	 * @param model
	 * @param selectedAnimal
	 * @return view name
	 */
	@RequestMapping(value="/FSAssign", method=RequestMethod.GET)
	public String DisplayAssignmentOptions(Model model, @ModelAttribute("animal") Animal selectedAnimal) {
		
		Log.controllerLog(Origin.CONTROLLER_FSASSIGN_GET, Level.INFO, "navigation");
		
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
		
		animalToAssign = selectedAnimal;
		
		context.close();
		return "FSAssign";
	}
	
	/**
	 * Calls DAO method to assign a feeding schedule to an animal
	 * 
	 * @param model
	 * @param fs
	 * @return
	 */
	@RequestMapping(value="/FSAssign", method=RequestMethod.POST)
	public String assignFeedingSchedule(Model model, @ModelAttribute("scheduleToAssign") FeedingSchedule fs) {
		
		Log.controllerLog(Origin.CONTROLLER_FSASSIGN_POST, Level.INFO, "navigation");
		
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		AnimalDAO animalDAO = context.getBean(AnimalDAO.class);
		FeedingScheduleDAO FSDAO = context.getBean(FeedingScheduleDAO.class);
		
		try {
			// animalDAO has no "getAnimalByID" method, so here's a work-around
			// could choose to go add one later, wouldn't be too hard (TODO)
			List<Animal> animals = animalDAO.getAllAnimals();
			Collections.sort(animals);		// unnecessary, but I like it
			Animal animal = new Animal();
			for (Animal a : animals) {		// need to fill in the gaps, animalToAssign only has ID field populated
				if (a.getAnimalID() == animalToAssign.getAnimalID()) {
					animal = a;
				}
			}
			
			FSDAO.assignFeedingSchedule(fs, animal);
				
			model.addAttribute("message",  "Feeding schedule successfully assigned");	
			model.addAttribute("messageClass", "alert-success");
			context.close();
			Log.controllerLog(Origin.CONTROLLER_FSASSIGN_POST, Level.INFO, "assignment successful");
			return "redirect:/animalCare";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message",  "There was a problem assigning or unassigning the feeding schedule at this time");
			model.addAttribute("messageClass",  "alert-danger");
			context.close();
			Log.controllerLog(Origin.CONTROLLER_FSASSIGN_POST, Level.ERROR, "unknown exception thrown");
			return "FSAssign";
		}
		
	}
}
