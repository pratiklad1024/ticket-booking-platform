package com.ticketplatform.demo.dto;

import java.time.LocalDateTime;

public record EventResponse(
		Long id,String title, String description, String venue, LocalDateTime eventDate, long totoalSeats) 
{

	public Long id() {
		return id;
	}

	public String title() {
		return title;
	}

	public String description() {
		return description;
	}

	public String venue() {
		return venue;
	}

	public LocalDateTime eventDate() {
		return eventDate;
	}

	public long totalSeats() {
		return totoalSeats;
	}
	

}
