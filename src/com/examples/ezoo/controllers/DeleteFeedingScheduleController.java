package com.examples.ezoo.controllers;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.examples.ezoo.config.Config;
import com.examples.ezoo.dao.AnimalDAO;
import com.examples.ezoo.dao.FeedingScheduleDAO;
import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.FeedingSchedule;

@Controller
public class DeleteFeedingScheduleController {

	@RequestMapping(value="deleteFeedingSchedule", method=RequestMethod.POST)
	public String deleteFeedingSchedule(@ModelAttribute("scheduleToDelete") FeedingSchedule scheduleToDelete) {
		
		// Call DAO method
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		FeedingScheduleDAO dao = context.getBean(FeedingScheduleDAO.class);
		AnimalDAO animalDAO = context.getBean(AnimalDAO.class);
		context.close();
		// TODO: deal with the message attribute commented out below
		try {
			// remove feeding schedule from all corresponding animals
			List<Animal> animals = animalDAO.getAllAnimals();
			for (Animal animal : animals) {
				if (animal.getFeedingScheduleID() == scheduleToDelete.getScheduleID()) {
					dao.removeFeedingSchedule(animal);
				}
			}
			
			// delete the feeding schedule
			dao.deleteFeedingSchedule(scheduleToDelete);
			
//			request.getSession().setAttribute("message",  "Feeding schedule successfully deleted");
//			request.getSession().setAttribute("messageClass", "alert-success");
			return "feedingSchedules";
		} catch(SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			// change the message
//			request.getSession().setAttribute("message",  "Id of " + scheduleToDelete.getScheduleID() + " does not exist");
//			request.getSession().setAttribute("messageClass",  "alert-danger");
			return "feedingSchedules";
		} catch (Exception e) {
			e.printStackTrace();
			// change the message
//			request.getSession().setAttribute("message",  "There was a problem deleting the feeding schedule at this time");
//			request.getSession().setAttribute("messageClass",  "alert-danger");
			return "feedingSchedules";
		}
	}
}
