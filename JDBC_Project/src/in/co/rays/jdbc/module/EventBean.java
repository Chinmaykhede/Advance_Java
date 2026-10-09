package in.co.rays.jdbc.module;

import java.sql.Date;

public class EventBean {
	private int eventId;
	private String eventName;
	private Date eventDate;
	private String venue;
	private String organizer;

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public String getOrganizer() {
		return organizer;
	}

	public void setOrganizer(String organizer) {
		this.organizer = organizer;
	}

}
