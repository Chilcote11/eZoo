package com.examples.ezoo.servlets;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Collections;
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
 * Servlet implementation class FSAssignmentServlet
 * 		calls the DAO method to assign/remove a feeding schedule from a given animal
 */
@WebServlet("/FSAssignment")
public class FSAssignmentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;		// default
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int animalID = Integer.parseInt(request.getParameter("animalID"));
		
		// Grab a list of Feeding Schedules from the Database
		FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDAO();
		List<FeedingSchedule> feedingSchedules = dao.getAllFeedingSchedules();

		// Populate `animals` field of each feeding schedule
		AnimalDAO animalDAO = DAOUtilities.getAnimalDAO();
		List<Animal> animals = animalDAO.getAllAnimals();
		Collections.sort(animals);
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
							"[" + animal.getAnimalID() + "]";	
				}
			}
			schedule.setAnimals(animalsWithSchedule); // remember, this purposefully never makes it to database
		}
		
		request.getSession().setAttribute("feedingSchedules", feedingSchedules);
		request.getSession().setAttribute("animalID", animalID);
		
		request.getRequestDispatcher("assignFeedingSchedule.jsp").forward(request, response);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get Parameters
		// We MUST convert to int since parameters are always Strings
		int animalID = Integer.parseInt(request.getParameter("animalID"));
		
		// Call DAO methods
		AnimalDAO animalDAO = DAOUtilities.getAnimalDAO();
		FeedingScheduleDAO FSDAO = DAOUtilities.getFeedingScheduleDAO();

		try {
			// animalDAO has no "getAnimalByID" method, so here's a work-around
			// could choose to go add one later, wouldn't be too hard
			List<Animal> animals = animalDAO.getAllAnimals();
			Collections.sort(animals);		// unnecessary, but I like it
			Animal animal = new Animal();
			for (Animal a : animals) {
				if (a.getAnimalID() == animalID)
					animal = a;
			}
			
			// unassignment logic
			if (animal.getFeedingScheduleID() > 0) {
				FSDAO.removeFeedingSchedule(animal);
				request.getSession().setAttribute("message",  "Feeding schedule successfully removed");
			}
			
			
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("animalCare");
		} catch (Exception e) {
			e.printStackTrace();
			
			// change the message
			request.getSession().setAttribute("message",  "There was a problem assigning or unassigning the feeding schedule at this time");
			request.getSession().setAttribute("messageClass",  "alert-danger");
			request.getRequestDispatcher("animalCareHome.jsp").forward(request, response);
		}
		
	}
}
