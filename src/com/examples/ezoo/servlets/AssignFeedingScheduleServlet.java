package com.examples.ezoo.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examples.ezoo.dao.DAOUtilities;
import com.examples.ezoo.dao.FeedingScheduleDAO;
import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.FeedingSchedule;

/**
 * Servlet implementation class AssignFeedingScheduleServlet
 * 		calls the DAO method to assign an existing feeding schedule to an animal
 * 		"this may not be necessary if each feeding schedule is unique and singular for each animal,
 * 				and you assign the animal when creating the feeding schedule"
 * 			--> it is necessary, multiple animals can have the same feeding schedule
 * 
 */
@WebServlet("/assignFeedingSchedule")
public class AssignFeedingScheduleServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;		// default
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("assignFeedingSchedule.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get Parameters
		// We MUST convert to int since parameters are always Strings
		
		// for the schedule:
		int scheduleID = Integer.parseInt(request.getParameter("scheduleID"));
		String feedingTime = request.getParameter("feedingTime");
		String recurrence = request.getParameter("recurrence");
		String food = request.getParameter("food");
		String notes = request.getParameter("notes");
		// Create a FeedingSchedule object from the parameters
		FeedingSchedule scheduleToAssign = new FeedingSchedule(
				scheduleID,
				feedingTime,
				recurrence,
				food,
				notes);
		
		// for the animal:
		long animalID = Long.parseLong(request.getParameter("animalID"));
		String name = request.getParameter("name");
		String kingdom = request.getParameter("kingdom");
		String phylum = request.getParameter("phylum");
		String clazz = request.getParameter("clazz");
		String order = request.getParameter("order");
		String family = request.getParameter("family");
		String genus = request.getParameter("genus");
		String species = request.getParameter("species");
		String type = request.getParameter("type");
		String healthStatus = request.getParameter("healthStatus");
		double height = Double.parseDouble(request.getParameter("height"));
		double weight = Double.parseDouble(request.getParameter("weight"));
		int feedingScheduleID = Integer.parseInt(request.getParameter("feedingScheduleID"));
		//Create an Animal object from the parameters
		Animal animalToBeFed = new Animal(
				animalID, 
				name, 
				kingdom,
				phylum,
				clazz,
				order,
				family,
				genus,
				species,
				height,
				weight,
				type,
				healthStatus, 
				feedingScheduleID);
		
		
		// Call DAO method
		FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDAO();
		try {
			dao.assignFeedingSchedule(scheduleToAssign, animalToBeFed);
			request.getSession().setAttribute("message",  "Feeding schedule successfully assigned");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("feedingSchedules");		// animal care servlet? "animalCare"
		/*} catch(SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			
			// change the message
			request.getSession().setAttribute("message",  "Id of " + scheduleToAssign.getScheduleID() + " is already in use");
			request.getSession().setAttribute("messageClass",  "alert-danger");
			request.getRequestDispatcher("assignFeedingSchedule.jsp").forward(request, response);
		 * 
		 * 			I don't think this is necessary, 
		 * 				we will use "assign" to change an animals feeding schedule
		 * */
		} catch (Exception e) {
			e.printStackTrace();
			
			// change the message
			request.getSession().setAttribute("message",  "There was a problem assigning the feeding schedule at this time");
			request.getSession().setAttribute("messageClass",  "alert-danger");
			request.getRequestDispatcher("assignFeedingSchedule.jsp").forward(request, response);
		}
	}
	
}
