package com.examples.ezoo.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * POJO used to represent EVENT_ATTENDEES table in database
 * 
 * Uses an EmbeddedId representing a composite key
 * 
 * Contains instructions for Spring validation.
 * 	Messages found in ValidationMessages.properties 
 * 
 * @author Cory Chilcote
 *
 */
@Entity
@Table(name="EVENT_ATTENDEES")
public class EventAttendee {

	@EmbeddedId
	private EmbeddedEventAttendee eventAttendee = new EmbeddedEventAttendee();
	
	public EventAttendee() {}
	
	public EventAttendee(EmbeddedEventAttendee eventAttendee) {
		this.eventAttendee = eventAttendee;
	}

	public EmbeddedEventAttendee getEventAttendee() {
		return eventAttendee;
	}

	public void setEventAttendee(EmbeddedEventAttendee eventAttendee) {
		this.eventAttendee = eventAttendee;
	}

	@Override
	public String toString() {
		return "EventAttendee [username=" + eventAttendee.getUsername() + 
				", eventID=" + eventAttendee.getEventID() + "]";
	}
	
	
}
