package com.examples.ezoo.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class EventAttendee implements Serializable{

	private static final long serialVersionUID = 1L;

	@NotNull
	private String username;
	
	@NotNull
	private Integer eventID;
	
	@Override public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || this.getClass() != o.getClass()) return false;
		
		EventAttendee that = (EventAttendee) o;
		
		if (!username.equals(that.username)) return false;
		return eventID.equals(that.eventID);
	}
	
	@Override
	public int hashCode() {
		int result = username.hashCode();
		result = 31 * result + eventID.hashCode();
		return result;
	}
}
