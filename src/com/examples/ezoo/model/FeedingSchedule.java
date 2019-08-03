package com.examples.ezoo.model;

import java.util.List;

public class FeedingSchedule implements Comparable<FeedingSchedule>{
	
	private int scheduleID = 0;
	private String feedingTime = "";
	private String recurrence = "";
	private String food = "";
	private String notes = "";
	private List<Animal> animals = new ArrayList<Animal>;
	
	public FeedingSchedule() {}
	
	public FeedingSchedule(int scheduleID, String feedingTime, String recurrence, String food, String notes) {
		super();
		this.scheduleID = scheduleID;
		this.feedingTime = feedingTime;
		this.recurrence = recurrence;
		this.food = food;
		this.notes = notes;
	}
	
	public int getScheduleID() {
		return scheduleID;
	}

	public void setScheduleID(int scheduleID) {
		this.scheduleID = scheduleID;
	}

	public String getFeedingTime() {
		return feedingTime;
	}

	public void setFeedingTime(String feedingTime) {
		this.feedingTime = feedingTime;
	}

	public String getRecurrence() {
		return recurrence;
	}

	public void setRecurrence(String recurrence) {
		this.recurrence = recurrence;
	}

	public String getFood() {
		return food;
	}

	public void setFood(String food) {
		this.food = food;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	public List<Animal> getAnimals() {
		return animals;
	}
		
	@Override
	public String toString() {
		if (this.scheduleID != 0)
			return "Feeding Schedule [scheduleID=" + scheduleID + ", feedingTime=" + feedingTime + ", recurrence=" + recurrence + ", food="
				+ food + ", notes=" + notes + "]";
		else
			return "No Feeding Schedule";
	}

	@Override
	public int compareTo(FeedingSchedule o) {
		return this.scheduleID - o.getScheduleID();
	}
	
}
