package com.examples.ezoo.controllers;

import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.examples.ezoo.config.Config;
import com.examples.ezoo.dao.AnimalDAO;
import com.examples.ezoo.dao.FeedingScheduleDAO;
import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.FeedingSchedule;

@Controller
public class FeedingSchedulesController {
	
	@RequestMapping(value="/feedingSchedules", method=RequestMethod.GET)
	public String DisplayFeedingSchedules(Model model) {
		
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
		
		// Populate the list into a variable that will be stored in the session
		model.addAttribute("feedingSchedules", feedingSchedules);
		
		return "feedingSchedules";
	}
}
