package com.examples.ezoo.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class EmbeddedEventAttendee implements Serializable{

	private static final long serialVersionUID = 1L;

	@NotNull
	private String username;
	
	@NotNull(message = "{eventid.validate}")
	private Integer event_id;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getEventID() {
		return event_id;
	}

	public void setEventID(Integer eventID) {
		this.event_id = eventID;
	}

	@Override public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || this.getClass() != o.getClass()) return false;
		
		EmbeddedEventAttendee that = (EmbeddedEventAttendee) o;
		
		if (!username.equals(that.username)) return false;
		return event_id.equals(that.event_id);
	}
	
	@Override
	public int hashCode() {
		int result = username.hashCode();
		result = 31 * result + event_id.hashCode();
		return result;
	}
}
