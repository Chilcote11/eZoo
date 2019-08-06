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
 * 		corresponds to createFeedingSchedule jsp
 * 		assessed from main drop down menu on animalCare page
 * 		
 */
@WebServlet("/createFeedingSchedule")
public class CreateFeedingScheduleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;		// default
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("createFeedingSchedule.jsp").forward(request, response);
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
			response.sendRedirect("feedingSchedules");
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
