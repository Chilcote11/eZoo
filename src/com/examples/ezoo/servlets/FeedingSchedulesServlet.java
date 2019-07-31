package com.examples.ezoo.servlets;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examples.ezoo.dao.DAOUtilities;
import com.examples.ezoo.dao.FeedingScheduleDAO;
import com.examples.ezoo.model.FeedingSchedule;


/**
 * Servlet implementation class FeedingSchedulesServlet
 */
@WebServlet(description = "This servlet is the main interface into the Feeding Schedules System", urlPatterns = { "/feedingSchedules" })
public class FeedingSchedulesServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Grab a list of Feeding Schedules from the Database
		FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDAO();
		List<FeedingSchedule> feedingSchedules = dao.getAllFeedingSchedules();

		// Populate the list into a variable that will be stored in the session
		request.getSession().setAttribute("feedingSchedules", feedingSchedules);
		
		/*Animal largest = new Animal();
		for (Animal a : animals)
			if (a.getWeight() > largest.getWeight())
				largest = a;
		request.getSession().setAttribute("largestAnimal", largest);
		
		Animal longest = new Animal();
		for (Animal a : animals)
			if (a.getName().length() > longest.getName().length())
				longest = a;
		request.getSession().setAttribute("longestNamedAnimal", longest);
		*/
					// maybe do things like this sometime?
		
		request.getRequestDispatcher("feedingSchedules.jsp").forward(request, response);
				// I'll need to create this at some point
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
		FeedingSchedule scheduleToDelete = new FeedingSchedule(
				scheduleID,
				feedingTime,
				recurrence,
				food,
				notes);
		
		// Call DAO method
		FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDAO();
		try {
			dao.deleteFeedingSchedule(scheduleToDelete);
			request.getSession().setAttribute("message",  "Feeding schedule successfully deleted");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("feedingSchedules");		// animal care servlet? "animalCare"
		} catch(SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			
			// change the message
			request.getSession().setAttribute("message",  "Id of " + scheduleToDelete.getScheduleID() + " does not exist");
			request.getSession().setAttribute("messageClass",  "alert-danger");
			request.getRequestDispatcher("deleteFeedingSchedule.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			
			// change the message
			request.getSession().setAttribute("message",  "There was a problem deleting the feeding schedule at this time");
			request.getSession().setAttribute("messageClass",  "alert-danger");
			request.getRequestDispatcher("deleteFeedingSchedule.jsp").forward(request, response);
		}
		
	}
	
}
