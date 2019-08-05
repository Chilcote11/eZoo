package com.examples.ezoo.dao;

import java.util.List;

import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.FeedingSchedule;

public interface FeedingScheduleDAO {
	
	void saveFeedingSchedule(FeedingSchedule schedule) throws Exception;
	void deleteFeedingSchedule(FeedingSchedule schedule) throws Exception;
	List<FeedingSchedule> getAllFeedingSchedules();
	FeedingSchedule getFeedingSchedule(Animal animal);				// gets feeding schedule for a specific animal
	void assignFeedingSchedule(FeedingSchedule feedingSchedule, Animal animal) throws Exception;						// assigns feeding schedule 
	void removeFeedingSchedule(Animal animal) throws Exception;						// remove feeding schedule from a given animal
	void updateFeedingSchedule(FeedingSchedule schedule) throws Exception;
}
