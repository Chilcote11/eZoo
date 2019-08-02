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
 * Servlet implementation class DeleteFeedingScheduleServlet
 * 		calls the DAO method to remove a feeding schedule from the database
 * 		and removes all references to that feeding schedule from Animals in the database
 */
@WebServlet("/deleteFeedingSchedule")
public class DeleteFeedingScheduleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;		// default
	
	/*@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("createFeedingSchedule.jsp").forward(request, response);
	}*/
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get Parameters
		// We MUST convert to int since parameters are always Strings
		
		// before input 
		/*@SuppressWarnings("unchecked")
		List<FeedingSchedule> feedingSchedules = (List<FeedingSchedule>) request.getAttribute("feedingSchedules");
//		Object feedingSchedules = request.getSession().getAttribute("feedingSchedules");
		System.out.println("feedingSchedules: " + feedingSchedules);		// prints: "feedingSchedules: null"
		FeedingSchedule schedule1 = feedingSchedules.get(0);
		System.out.println("schedule1 : ");
		System.out.println(schedule1);		// NullPointerException thrown at this line
		*/
		
		// after input
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
			response.sendRedirect("feedingSchedules");		// need this
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
