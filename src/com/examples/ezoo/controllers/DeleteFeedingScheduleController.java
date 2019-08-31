package com.examples.ezoo.controllers;

import java.sql.SQLIntegrityConstraintViolationException;
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

@Controller
public class DeleteFeedingScheduleController {
	
	private ZooLogger Log = new ZooLogger();

	@RequestMapping(value="/FSDelete", method=RequestMethod.POST)
	public String deleteFeedingSchedule(Model model, @ModelAttribute("scheduleToDelete") FeedingSchedule scheduleToDelete) {

		Log.controllerLog(Origin.CONTROLLER_FSDELETE_POST, Level.INFO, "navigation");
		
		// Call DAO method
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		FeedingScheduleDAO dao = context.getBean(FeedingScheduleDAO.class);
		AnimalDAO animalDAO = context.getBean(AnimalDAO.class);
//		context.close();
		try {
			// remove feeding schedule from all corresponding animals
			List<Animal> animals = animalDAO.getAllAnimals();
			for (Animal animal : animals) {
				if (animal.getFeedingScheduleID() != null) {
					if (animal.getFeedingScheduleID() == scheduleToDelete.getScheduleID()) {
						dao.removeFeedingSchedule(animal);
					}
				}
			}
			
			// delete the feeding schedule
			dao.deleteFeedingSchedule(scheduleToDelete);

			model.addAttribute("message",  "Feeding schedule successfully deleted");
			model.addAttribute("messageClass", "alert-success");
			context.close();
			Log.controllerLog(Origin.CONTROLLER_FSDELETE_POST, Level.INFO, "deletion successful");
			return "redirect:/feedingSchedules";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message",  "There was a problem deleting the feeding schedule at this time");
			model.addAttribute("messageClass",  "alert-danger");
			context.close();
			Log.controllerLog(Origin.CONTROLLER_FSDELETE_POST, Level.ERROR, "unknown exception thrown");
			return "redirect:/feedingSchedules";
		}
	}
}
