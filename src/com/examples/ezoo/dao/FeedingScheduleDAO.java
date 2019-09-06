package com.examples.ezoo.dao;

import java.util.List;

import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.FeedingSchedule;

public interface FeedingScheduleDAO {
	
	void saveFeedingSchedule(FeedingSchedule schedule) throws Exception;						// save a feeding schedule to the database
	void deleteFeedingSchedule(FeedingSchedule schedule) throws Exception;						// delete a feeding schedule
	List<FeedingSchedule> getAllFeedingSchedules();												// retrieve all 
	FeedingSchedule getFeedingSchedule(Animal animal);											// get feeding schedule for a specific animal
	void assignFeedingSchedule(FeedingSchedule feedingSchedule, Animal animal) throws Exception;// assign a feeding schedule to an animal 
	void removeFeedingSchedule(Animal animal) throws Exception;									// remove a feeding schedule from a given animal
	void updateFeedingSchedule(FeedingSchedule schedule) throws Exception;						// update a feeding schedule
}
