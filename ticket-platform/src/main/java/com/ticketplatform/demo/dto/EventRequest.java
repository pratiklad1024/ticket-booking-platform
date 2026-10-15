package com.ticketplatform.demo.dto;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EventRequest(
		@NotBlank(message = "Title is required")
		@Size(max = 100)
		 String title,
		
		 String description,
		
		@NotBlank(message = "Venue is required")
		@Size(max = 50)
		 String venue,
		 
		@NotNull(message = "Event date is required")
		@Future(message = "Event date must be in future")
	     LocalDateTime eventDate,
		
		@Min(value = 1, message = "Total seats must be at least 1")
		 long totalSeats) {

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
		return totalSeats;
	}
	

}
