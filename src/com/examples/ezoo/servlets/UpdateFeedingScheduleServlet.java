package com.examples.ezoo.servlets;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examples.ezoo.dao.DAOUtilities;
import com.examples.ezoo.dao.FeedingScheduleDAO;
import com.examples.ezoo.model.FeedingSchedule;


/**
 * Servlet implementation class CreateFeedingScheduleServlet
 * 		call the DAO method to create a new feeding schedule in the database
 * 		
 */
@WebServlet("/updateFeedingSchedule")
public class UpdateFeedingScheduleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;		// default
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int oldScheduleID = Integer.parseInt(request.getParameter("scheduleID"));
		
		String oldFeedingTime = request.getParameter("feedingTime");
		String oldRecurrence = request.getParameter("recurrence");
		String oldFood = request.getParameter("food");
		String oldNotes = request.getParameter("notes");
		
		// Create a FeedingSchedule object from the parameters
		FeedingSchedule oldFeedingSchedule = new FeedingSchedule(
				oldScheduleID,
				oldFeedingTime,
				oldRecurrence,
				oldFood,
				oldNotes);
		
		request.getSession().setAttribute("oldFeedingSchedule", oldFeedingSchedule);
		
		request.getRequestDispatcher("updateFeedingSchedule.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get Parameters
		// We MUST convert to int since parameters are always Strings
		
		int scheduleID = Integer.parseInt(request.getParameter("scheduleID"));
		
		String feedingTime = request.getParameter("feedingTime");
		String recurrence = request.getParameter("recurrence");
		String food = request.getParameter("food");
		String notes = request.getParameter("notes");
		
		// Create a FeedingSchedule object from the parameters
		FeedingSchedule scheduleToSave = new FeedingSchedule(
				scheduleID,
				feedingTime,
				recurrence,
				food,
				notes);
		
		// Call DAO method
		FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDAO();
		try {
			dao.saveFeedingSchedule(scheduleToSave);
			request.getSession().setAttribute("message",  "Feeding schedule successfully created");
			request.getSession().setAttribute("messageClass", "alert-success");
//			response.sendRedirect("feedingSchedules");		// animal care servlet? "animalCare"
			response.sendRedirect("feedingSchedules");		// animal care servlet? "animalCare"
		} catch(SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			
			// change the message
			request.getSession().setAttribute("message",  "Id of " + scheduleToSave.getScheduleID() + " is already in use");
			request.getSession().setAttribute("messageClass",  "alert-danger");
			request.getRequestDispatcher("createFeedingSchedule.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			
			// change the message
			request.getSession().setAttribute("message",  "There was a problem creating the feeding schedule at this time");
			request.getSession().setAttribute("messageClass",  "alert-danger");
			request.getRequestDispatcher("createFeedingSchedule.jsp").forward(request, response);
		}
		
	}
	
}