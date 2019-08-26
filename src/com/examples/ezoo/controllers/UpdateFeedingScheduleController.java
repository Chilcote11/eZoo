package com.examples.ezoo.controllers;

import javax.validation.Valid;

import org.apache.logging.log4j.Level;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
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

@Controller
public class UpdateFeedingScheduleController {
	
	private ZooLogger Log = new ZooLogger();

	@RequestMapping(value="/updateFeedingSchedule", method=RequestMethod.GET)
	public String DisplayUpdatePage(Model model, @ModelAttribute("scheduleToUpdate") FeedingSchedule scheduleToUpdate
			, @ModelAttribute("message") String message
			, @ModelAttribute("messageClass") String messageClass) {
		
		Log.controllerLog(Origin.CONTROLLER_FSUPDATE_GET, Level.INFO, "navigation");
		
		model.addAttribute("scheduleToUpdate", scheduleToUpdate);	// transfer
		return "updateFeedingSchedule";
	}
	
	@RequestMapping(value="/updateFeedingSchedule", method=RequestMethod.POST)
	public String updateFeedingSchedule(Model model, @Valid @ModelAttribute("scheduleToUpdate") FeedingSchedule scheduleToUpdate, Errors errors) {
		
		Log.controllerLog(Origin.CONTROLLER_FSUPDATE_POST, Level.INFO, "navigation");
		
		if (errors.hasErrors()) {
			Log.controllerLog(Origin.CONTROLLER_FSUPDATE_POST, Level.WARN, "validation errors");
			
			model.addAttribute("message",  "Missing or invalid entries! Please try again");
			model.addAttribute("messageClass",  "alert-danger");
			
			return "updateFeedingSchedule";
		}
		
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		FeedingScheduleDAO dao = context.getBean(FeedingScheduleDAO.class);
		
		// Call DAO method
		try {
			dao.updateFeedingSchedule(scheduleToUpdate);
			model.addAttribute("message",  "Feeding schedule successfully updated");
			model.addAttribute("messageClass", "alert-success");
			context.close();
			Log.controllerLog(Origin.CONTROLLER_FSUPDATE_POST, Level.INFO, "update successful");
			return "redirect:/feedingSchedules";
		} catch (Exception e) {
			e.printStackTrace();;
			model.addAttribute("message",  "There was a problem updating the feeding schedule at this time");
			model.addAttribute("messageClass",  "alert-danger");
			context.close();
			Log.controllerLog(Origin.CONTROLLER_FSUPDATE_POST, Level.ERROR, "unknown exception thrown");
			return "updateFeedingSchedules";
		}
	}

}
