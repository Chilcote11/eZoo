package com.examples.ezoo.controllers;

import java.sql.SQLIntegrityConstraintViolationException;

import javax.validation.Valid;

import org.apache.logging.log4j.Level;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.examples.ezoo.config.Config;
import com.examples.ezoo.dao.FeedingScheduleDAO;
import com.examples.ezoo.logger.Origin;
import com.examples.ezoo.logger.ZooLogger;
import com.examples.ezoo.model.FeedingSchedule;

/**
 * Allows users to create new feeding schedules and save them to the database
 * 
 * @author Cory Chilcote
 *
 */
@Controller
public class FSCreateController {
	
	private ZooLogger Log = new ZooLogger();

	/**
	 * Prepares attributes for feeding schedule creation form
	 * 
	 * @param model
	 * @return view name
	 */
	@RequestMapping(value="/FSCreate", method=RequestMethod.GET)
	public String DisplayCreateFeedingScheduleForm(Model model) {
		
		Log.controllerLog(Origin.CONTROLLER_FSCREATE_GET, Level.INFO, "navigation");
		
		model.addAttribute("newFeedingSchedule", new FeedingSchedule());
		return "FSCreate";		
	}
	
	/**
	 * Validates form inputs and calls DAO method to delete an animal from the database
	 * 
	 * @param model
	 * @param newFS
	 * @param errors
	 * @return view name
	 */
	@RequestMapping(value="/FSCreate", method=RequestMethod.POST)
	public String createFeedingSchedule(Model model, @Valid @ModelAttribute("newFeedingSchedule") FeedingSchedule newFS, Errors errors) {
		
		Log.controllerLog(Origin.CONTROLLER_FSCREATE_POST, Level.INFO, "navigation");
		
		if (errors.hasErrors()) {
			Log.controllerLog(Origin.CONTROLLER_FSCREATE_POST, Level.WARN, "validation errors");
			
			model.addAttribute("message",  "Missing or invalid entries! Please try again");
			model.addAttribute("messageClass",  "alert-danger");
			
			return "FSCreate";
		}
		
		// Call DAO method
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		FeedingScheduleDAO dao = context.getBean(FeedingScheduleDAO.class);
		
		try {
			dao.saveFeedingSchedule(newFS);
			context.close();
			model.addAttribute("message",  "Feeding schedule successfully created");
			model.addAttribute("messageClass", "alert-success");
			Log.controllerLog(Origin.CONTROLLER_FSCREATE_POST, Level.INFO, "save successful");
			return "redirect:/feedingSchedules";
		} catch(DataIntegrityViolationException e) {
			e.printStackTrace();
			model.addAttribute("message",  "Id of " + newFS.getScheduleID() + " is already in use");
			model.addAttribute("messageClass",  "alert-danger");
			context.close();
			Log.controllerLog(Origin.CONTROLLER_FSCREATE_POST, Level.ERROR, "exception: duplicate scheduleID");
			return "FSCreate";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message",  "There was a problem creating the feeding schedule at this time");
			model.addAttribute("messageClass",  "alert-danger");
			context.close();
			Log.controllerLog(Origin.CONTROLLER_FSCREATE_POST, Level.ERROR, "unknown exception thrown");
			return "FSCreate";
		}
	}
}
