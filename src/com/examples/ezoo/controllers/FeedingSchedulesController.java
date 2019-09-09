package com.examples.ezoo.controllers;

import java.util.Collections;
import java.util.List;

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
import com.examples.ezoo.dao.FeedingScheduleDAO;
import com.examples.ezoo.logger.Origin;
import com.examples.ezoo.logger.ZooLogger;
import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.FeedingSchedule;

/** 
 * Allows users to view and interact with the zoo's various feeding schedules
 * 
 * @author Cory Chilcote
 *
 */
@Controller
public class FeedingSchedulesController {
	
	private ZooLogger Log = new ZooLogger();
	
	/**
	 * Prepares attributes for the page that displays all feeding schedules
	 * 
	 * @param model
	 * @return view name
	 */
	@RequestMapping(value="/feedingSchedules", method=RequestMethod.GET)
	public String DisplayFeedingSchedules(Model model) {
		
		Log.controllerLog(Origin.CONTROLLER_FS_GET, Level.INFO, "navigation");
		
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
			for (Animal animal : animals) {
				if (animal.getFeedingScheduleID() != null) {
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
			}
			schedule.setAnimals(animalsWithSchedule); // remember, this purposefully never makes it to database
		}
		
		// Populate the list into a variable that will be stored in the model
		model.addAttribute("feedingSchedules", feedingSchedules);
		
		// need this, because there is no GET method for the delete action
		model.addAttribute("scheduleToDelete", new FeedingSchedule());
		
		// need this to send the correct feeding schedule to update page
		model.addAttribute("scheduleToUpdate", new FeedingSchedule());
		
		context.close();
		return "feedingSchedules";
	}
}
