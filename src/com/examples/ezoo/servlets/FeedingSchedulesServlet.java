package com.examples.ezoo.servlets;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examples.ezoo.dao.AnimalDAO;
import com.examples.ezoo.dao.DAOUtilities;
import com.examples.ezoo.dao.FeedingScheduleDAO;
import com.examples.ezoo.model.Animal;
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
		
		// Grab all animals by feeding schedule
		AnimalDAO animalDAO = DAOUtilities.getAnimalDAO();
		List<Animal> animals = animalDAO.getAllAnimals();
		for (FeedingSchedule schedule : feedingSchedules) {
			String animalsWithSchedule = "";
			int count = 0;
			for (Animal animal : animals) {
				if (schedule.getScheduleID() == animal.getFeedingScheduleID()) {
					count++;
					String comma = "";
					if (count > 1) {
						comma = ", ";
					}
					animalsWithSchedule += comma + animal.getName() + 
							"[" + animal.getFeedingScheduleID() + "]";
				}
			}
			schedule.setAnimals(animalsWithSchedule); // remember, this never makes it to database
		}

		// Populate the list into a variable that will be stored in the session
		request.getSession().setAttribute("feedingSchedules", feedingSchedules);
				
		request.getRequestDispatcher("feedingSchedules.jsp").forward(request, response);
	}
		
}