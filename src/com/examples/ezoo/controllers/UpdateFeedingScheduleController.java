package com.examples.ezoo.controllers;

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
public class UpdateFeedingScheduleController {

	@RequestMapping(value="/updateFeedingSchedule", method=RequestMethod.GET)
	public String DisplayUpdatePage(Model model, @ModelAttribute("scheduleToUpdate") FeedingSchedule scheduleToUpdate) {
//			, @ModelAttribute("message") String message
//			, @ModelAttribute("messageClass") String messageClass) {
		
		// clear in new model
		model.addAttribute("message", null);
		model.addAttribute("messageClass", null);
		
		model.addAttribute("scheduleToUpdate", scheduleToUpdate);	// transfer
		return "updateFeedingSchedule";
	}
	
	@RequestMapping(value="/updateFeedingSchedule", method=RequestMethod.POST)
	public String updateFeedingSchedule(Model model, /*@Valid*/ @ModelAttribute("scheduleToUpdate") FeedingSchedule scheduleToUpdate/*, Errors errors*/) {
//			, @ModelAttribute("message") String message
//			, @ModelAttribute("messageClass") String messageClass) {
		
		// not setting in new model for now
//		model.addAttribute("message", message);
//		model.addAttribute("messageClass", messageClass);
		
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		FeedingScheduleDAO dao = context.getBean(FeedingScheduleDAO.class);
//		context.close();
		// TODO: deal with the message attribute commented out below
		
		// Call DAO method
		try {
			dao.updateFeedingSchedule(scheduleToUpdate);
//			request.getSession().setAttribute("message",  "Feeding schedule successfully updated");
//			request.getSession().setAttribute("messageClass", "alert-success");
			model.addAttribute("message",  "Feeding schedule successfully updated");
			model.addAttribute("messageClass", "alert-success");
			context.close();
			return "feedingSchedules";
		} catch (Exception e) {
			e.printStackTrace();
//			request.getSession().setAttribute("message",  "There was a problem updating the feeding schedule at this time");
//			request.getSession().setAttribute("messageClass",  "alert-danger");
			model.addAttribute("message",  "There was a problem updating the feeding schedule at this time");
			model.addAttribute("messageClass",  "alert-danger");
			context.close();
			return "updateFeedingSchedules";
		}
	}

}
