package com.examples.ezoo.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.aspectj.lang.annotation.After;

@Entity
@Table(name="EVENTS")
public class Event implements Comparable<Event>{

	@Id @Column(name="event_id")
	@NotNull(message = "{eventid.validate}")
	private Integer eventID = 0;
	
	@Column(name="name")
	@NotEmpty(message = "{eventname.validate}")
	private String eventName = "";
	
	@Column(name="date")
	@Future(message = "{eventdate.validate}")
	private LocalDate eventDate = LocalDate.now();	// unsure if this is a good initialization
	
	@Column
	@NotEmpty(message = "{eventdescription.validate}")
	private String description = "";
	
	public Event() {}
	
	public Event(Integer eventID, String eventName, LocalDate eventDate, String description) {
		super();
		this.eventID = eventID;
		this.eventName = eventName;
		this.eventDate = eventDate;
		this.description = description;
	}

	public Integer getEventID() {
		return eventID;
	}

	public void setEventID(Integer eventID) {
		this.eventID = eventID;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public LocalDate getEventDate() {
		return eventDate;
	}

	public void setEventDate(LocalDate eventDate) {
		this.eventDate = eventDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int compareTo(Event o) {
		return this.getEventID() - o.getEventID();
	}

	@Override
	public String toString() {
		return "Event [eventID=" + eventID + ", eventName=" + eventName + ", eventDate=" + eventDate + ", description="
				+ description + "]";
	}	

}
