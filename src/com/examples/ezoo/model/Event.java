package com.examples.ezoo.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.aspectj.lang.annotation.After;
import org.springframework.format.annotation.DateTimeFormat;

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
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
//	@Future(message = "{eventdate.future.validate}")
	private LocalDateTime eventDate = LocalDateTime.now();	// unsure if this is a good initialization
	
	@Column
	@NotEmpty(message = "{eventdescription.validate}")
	private String description = "";
	
	public Event() {}
	
	public Event(Integer eventID, String eventName, LocalDateTime eventDate, String description) {
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

	public LocalDateTime getEventDate() {
		return eventDate;
	}

	public void setEventDate(LocalDateTime eventDate) {
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
