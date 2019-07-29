package com.examples.ezoo.dao;

import java.util.Collections;
import java.util.List;
import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.FeedingSchedule;

/*
 * This test class assumes:
 * 		There are no feeding schedules with ID 400 or 401 already existing
 * 		A feeding schedule with id=1 exists (preferrably containing giraffes)
 * 		Leo starts with id=1 and feeding_schedule = 1 (eating giraffes)
 * 		Animal(id=4) exists (preferrably named "hungry") and is originally assigned no feeding schedule
 * 
 * This test class makes changes, but resets all values to their original state upon completion
 */

public class TestFeedingScheduleDAO {

	public static void main(String[] args) {
	    
		FeedingScheduleDAO dao = new FeedingScheduleDAOImpl();
		AnimalDAO animalDAO = new AnimalDAOImpl();
	    
	    
	    // test saveFeedingSchedule(FeedingSchedule feedingSchedule) throws Exception
	    FeedingSchedule feedingSchedule1 = new FeedingSchedule(400, "0800", "daily", "oatmeal", "breakfast");
	    FeedingSchedule feedingSchedule2 = new FeedingSchedule(401, "1300", "daily", "penguin", "lunch");
	    try {
	    	dao.saveFeedingSchedule(feedingSchedule1);
			dao.saveFeedingSchedule(feedingSchedule2);
		} catch (Exception e) {
			System.out.println(e);
		}
	    
	    
	    // test getAllFeedingSchedules()
	    List<FeedingSchedule> allFeedingSchedules = dao.getAllFeedingSchedules();
	    Collections.sort(allFeedingSchedules);										// Don't forget to sort
	    for (int i = 0; i < allFeedingSchedules.size(); i++){
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
	    	/*for (FeedingSchedule schedule : allFeedingSchedules) {
	    		if (schedule.getScheduleID() == 1)	{		// feeding schedule 1 food = giraffes
	    			for (Animal animal : animals) {
	    				if (animal.getAnimalID() == 4) {	// animal 4 is hungry
	    					dao.assignFeedingSchedule(schedule, animal);		// feed the hungry animal giraffes
	    				}
	    			}
	    		}
	    	}*/	
	    	// if these lists were sorted, the line below could replace the previous 9:
	    	dao.assignFeedingSchedule(allFeedingSchedules.get(1-1), animals.get(4-1));
					// UPDATE: using comparable interface and sorting the list (see line 47)
		} catch (Exception e) {
			System.out.println(e);
		}
	    
	    
	    // test removeFeedingSchedule(Animal animal) throws Exception
	    try {
	    	/*for (Animal animal : animals) {
				if (animal.getAnimalID() == 1) {	// animal 1 is Leo
					dao.removeFeedingSchedule(animal);		// Leo could lose some weight
				}
			}*/
	    		// UPDATE: animals is now sorted
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
	    	/*for (FeedingSchedule schedule : allFeedingSchedules) {
	    		if (schedule.getScheduleID() == 1)	{		// feeding schedule 1 has giraffes
	    			for (Animal animal : animals) {
	    				if (animal.getAnimalID() == 1) {	// animal 1 is Leo
	    					dao.assignFeedingSchedule(schedule, animal);		// let the lion eat
	    				}
	    			}
	    		}
	    	}*/
	    		// UPDATE: both animals and feeding schedules are now sorted
	    	dao.assignFeedingSchedule(allFeedingSchedules.get(1-1), animals.get(1-1));		// let the lion eat
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    try {
	    	/*for (Animal animal : animals) {
				if (animal.getAnimalID() == 4) {	// animal 4 is hungry
					dao.removeFeedingSchedule(animal);		// hungry back on diet
				}
			}*/
	    		// UPDATE: animals is now sorted
	    	dao.removeFeedingSchedule(animals.get(4-1));
		} catch (Exception e) {
			System.out.println(e);
		}
	    
	}
}
