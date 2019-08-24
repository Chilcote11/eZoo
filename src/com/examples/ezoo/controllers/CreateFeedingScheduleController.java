package com.examples.ezoo.controllers;

import java.sql.SQLIntegrityConstraintViolationException;

import javax.validation.Valid;

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
import com.examples.ezoo.model.FeedingSchedule;

@Controller
public class CreateFeedingScheduleController {

	@RequestMapping(value="/createFeedingSchedule", method=RequestMethod.GET)
	public String DisplayCreateFeedingScheduleForm(Model model
			, @ModelAttribute("message") String messge
			, @ModelAttribute("messageClass") String messageClass) {
		
		model.addAttribute("newFeedingSchedule", new FeedingSchedule());
		return "createFeedingSchedule";		
	}
	
	@RequestMapping(value="/createFeedingSchedule", method=RequestMethod.POST)
	public String createFeedingSchedule(Model model, @Valid @ModelAttribute("newFeedingSchedule") FeedingSchedule newFS, Errors errors) {
		
		if (errors.hasErrors())
			return "createFeedingSchedule";
		
		// Call DAO method
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		FeedingScheduleDAO dao = context.getBean(FeedingScheduleDAO.class);
		
		try {
			dao.saveFeedingSchedule(newFS);
			context.close();
			model.addAttribute("message",  "Feeding schedule successfully created");
			model.addAttribute("messageClass", "alert-success");
			return "redirect:/feedingSchedules";
		} catch(DataIntegrityViolationException e) {
			e.printStackTrace();
			model.addAttribute("message",  "Id of " + newFS.getScheduleID() + " is already in use");
			model.addAttribute("messageClass",  "alert-danger");
			context.close();
			return "createFeedingSchedule";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message",  "There was a problem creating the feeding schedule at this time");
			model.addAttribute("messageClass",  "alert-danger");
			context.close();
			return "createFeedingSchedule";
		}
	}
}
