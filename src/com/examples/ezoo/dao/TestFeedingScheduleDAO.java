package com.examples.ezoo.dao;

import java.util.Collections;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.examples.ezoo.config.Config;
import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.FeedingSchedule;

/**
 * Test script to verify FeedingScheduleDAOImpl methods 
 * 
 * Assumptions:
 * 		+ There are no feeding schedules with ID 400 or 401 already existing
 * 		+ A feeding schedule with id=1 exists (preferrably containing giraffes as food)
 * 		+ Leo starts with id=1 and feeding_schedule = 1 (eating giraffes)
 * 		+ Animal(id=4) exists (preferrably named "hungry") and is originally assigned no feeding schedule
 * 
 * This test class makes changes to the database, 
 * 	but resets all values to their original state upon completion
 * 
 * Sorting (using Collections.sort) is important for several of these methods; 
 * 	if unsorted the wrong feeding schedule could be operated on
 * 
 * @author Cory Chilcote
 * 
 */
public class TestFeedingScheduleDAO {

	public static void main(String[] args) {
	    
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		FeedingScheduleDAO dao = (FeedingScheduleDAO) context.getBean(FeedingScheduleDAO.class);
		AnimalDAO animalDAO = (AnimalDAO) context.getBean(AnimalDAO.class);
	    
	    
	    // test saveFeedingSchedule(FeedingSchedule feedingSchedule) throws Exception
	    FeedingSchedule feedingSchedule1 = new FeedingSchedule(400, "0800", "daily", "oatmeal", "breakfast");
	    FeedingSchedule feedingSchedule2 = new FeedingSchedule(401, "1300", "daily", "penguin", "lunch");
	    try {
	    	dao.saveFeedingSchedule(feedingSchedule1);
			dao.saveFeedingSchedule(feedingSchedule2);
		} catch (Exception e) {
			System.out.println(e);
		}
	    
	    // test updateFeedingSchedule(FeedingSchedule feedingSchedule) throws Exception
	    feedingSchedule1.setRecurrence("weekly");	    
	    try {
	    	dao.updateFeedingSchedule(feedingSchedule1);
	    } catch ( Exception e ) {
	    	System.out.println(e);
	    }
	    
	    
	    // test getAllFeedingSchedules()
	    List<FeedingSchedule> allFeedingSchedules = dao.getAllFeedingSchedules();
	    Collections.sort(allFeedingSchedules);										// Don't forget to sort
	    for (int i = 0; i < allFeedingSchedules.size(); i++) {
	      FeedingSchedule f = allFeedingSchedules.get(i);
	      System.out.println(f);
	    }
	    
	    
	    // test getFeedingSchedule(Animal animal)
	    List<Animal> animals = animalDAO.getAllAnimals();
	    Collections.sort(animals);													// Don't forget to sort
	    for (Animal a : animals) {
	    	System.out.println(a.getName() + " has feeding schedule: ");
	    	System.out.println(dao.getFeedingSchedule(a));
	    }
	    
	    
	    // test assignFeedingSchedule(Animal animal) throws Exception
	    try {
	    	dao.assignFeedingSchedule(allFeedingSchedules.get(1-1), animals.get(4-1));
		} catch (Exception e) {
			System.out.println(e);
		}
	    
	    
	    // test removeFeedingSchedule(Animal animal) throws Exception
	    try {
	    	dao.removeFeedingSchedule(animals.get(1-1));
		} catch (Exception e) {
			System.out.println(e);
		}
	    
	    
	    // test deleteFeedingSchedule(FeedingSchedule feedingSchedule) throws Exception
	    try {
			dao.deleteFeedingSchedule(feedingSchedule1);		// no more oatmeal
			dao.deleteFeedingSchedule(feedingSchedule2);		// lunch is cancelled
		} catch (Exception e) {
			System.out.println(e);
		}
	    
	    
	    
	    // the statements below reset values back to their original state
	    try {
	    	dao.assignFeedingSchedule(allFeedingSchedules.get(1-1), animals.get(1-1));		// let the lion eat
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	    try {
	    	dao.removeFeedingSchedule(animals.get(4-1));
		} catch (Exception e) {
			System.out.println(e);
		}
	    
	    context.close();
	    
	}
}
