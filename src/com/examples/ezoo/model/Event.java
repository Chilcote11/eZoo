package com.examples.ezoo.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.aspectj.lang.annotation.After;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * POJO used to represent EVENTS table in database
 * 
 * Contains instructions for Spring validation.
 * 	Messages found in ValidationMessages.properties 
 * 
 * @author Cory Chilcote
 * @since 2.0
 *
 */
@Entity
@Table(name="EVENTS")
public class Event implements Comparable<Event>{

	@Id @Column(name="event_id")
	@NotNull(message = "{eventid.validate}")
	private Integer eventID = 0;
	
	@Column(name="name")
	@NotEmpty(message = "{eventname.validate}")
	private String eventName = "";
	
	@Column
	@NotEmpty(message = "{eventdescription.validate}")
	private String description = "";
	
	@Column(name="start_time")
	@DateTimeFormat(pattern = "MM/dd/yyyy HH:mm")
	private LocalDateTime startTime = LocalDateTime.now();	
	
	@Column(name="end_time")
	@DateTimeFormat(pattern = "MM/dd/yyyy HH:mm")
	private LocalDateTime endTime = LocalDateTime.now().plusHours(2);
	
	@Column(name="creator")
	@NotEmpty(message = "{eventcreator.validate}")
	private String creator = "";
	
	@Transient						// never sees the database
	private int numberAttending = 0;
	
	
	public Event() {}

	public Event(Integer eventID, String eventName, String description, 
			LocalDateTime startTime, LocalDateTime endTime, String creator) {
		super();
		this.eventID = eventID;
		this.eventName = eventName;
		this.description = description;
		this.startTime = startTime;
		this.endTime = endTime;
		this.creator = creator;
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


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public LocalDateTime getStartTime() {
		return startTime;
	}


	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}


	public LocalDateTime getEndTime() {
		return endTime;
	}


	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}


	public String getCreator() {
		return creator;
	}


	public void setCreator(String creator) {
		this.creator = creator;
	}

	public int getNumberAttending() {
		return numberAttending;
	}

	public void setNumberAttending(int numberAttending) {
		this.numberAttending = numberAttending;
	}

	@Override
	public String toString() {
		return "Event [eventID=" + eventID + ", eventName=" + eventName + ", description=" + description
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", creator=" + creator + ", numberAttending="
				+ numberAttending + "]";
	}

	@Override
	public int compareTo(Event o) {
		return this.eventID - o.getEventID();
	}

}
