package com.examples.ezoo.controllers;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.examples.ezoo.config.Config;
import com.examples.ezoo.dao.FeedingScheduleDAO;
import com.examples.ezoo.model.FeedingSchedule;

@Controller
public class CreateFeedingScheduleController {

	@RequestMapping(value="/createFeedingSchedule", method=RequestMethod.GET)
	public String DisplayCreateFeedingScheduleForm(Model model) {
		model.addAttribute("newFeedingSchedule", new FeedingSchedule());
		return "createFeedingSchedule";		
	}
	
	@RequestMapping(value="createFeedingSchedule", method=RequestMethod.GET)
	public String createFeedingSchedule(/*@Valid*/ @ModelAttribute("newFeedingSchedule") FeedingSchedule newFS/*, Errors errors*/) {
		
		// Get Parameters
		int scheduleID = newFS.getScheduleID();
		String feedingTime = newFS.getFeedingTime();
		String recurrence = newFS.getRecurrence();
		String food = newFS.getFood();
		String notes = newFS.getNotes();
		
		// Create a FeedingSchedule object from the parameters
		FeedingSchedule scheduleToSave = new FeedingSchedule(
				scheduleID,
				feedingTime,
				recurrence,
				food,
				notes);
		
		// Call DAO method
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		FeedingScheduleDAO dao = context.getBean(FeedingScheduleDAO.class);
		context.close();
		// TODO: deal with the message attribute commented out below
		try {
			dao.saveFeedingSchedule(scheduleToSave);
//			request.getSession().setAttribute("message",  "Feeding schedule successfully created");
//			request.getSession().setAttribute("messageClass", "alert-success");
			return "feedingSchedules";
		} catch(SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			// change the message
//			request.getSession().setAttribute("message",  "Id of " + scheduleToSave.getScheduleID() + " is already in use");
//			request.getSession().setAttribute("messageClass",  "alert-danger");
			return "createFeedingSchedule";
		} catch (Exception e) {
			e.printStackTrace();
			// change the message
//			request.getSession().setAttribute("message",  "There was a problem creating the feeding schedule at this time");
//			request.getSession().setAttribute("messageClass",  "alert-danger");
			return "createFeedingSchedule";
		}
	}
}
