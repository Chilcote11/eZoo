package com.examples.ezoo.servlets;

import java.io.IOException;
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

		// Grab a list of Animals from the Database
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
		
		request.getRequestDispatcher("FeedingSchedulesHome.jsp").forward(request, response);
				// I'll need to create this at some point
	}
}
