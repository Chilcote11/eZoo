package com.examples.ezoo.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

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
		return "EventAttendee [eventAttendee=" + eventAttendee + "]";
	}
	
	
}
