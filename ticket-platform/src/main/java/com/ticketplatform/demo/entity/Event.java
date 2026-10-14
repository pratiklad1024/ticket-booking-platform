package com.ticketplatform.demo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "events")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	
	@NotBlank(message = "Title is required")
	@Size(max = 100)
	private String title;
	
	private String description;
	
	@NotBlank(message = "Venue is required")
	@Size(max = 50)
	private String venue;
	@Column(name = "event_date")
	
	@NotNull(message = "Event date is required")
	@Future(message = "Event date must be in future")
	private LocalDateTime eventDate;
	
	@Min(value = 1, message = "Total seats must be at least 1")
	@Column(name = "total_seats")
	private long totalSeats;
	
	@Column(name = "creationtime")
	private LocalDateTime creationTime;
	
	@Column(name = "updatedtime")
	private LocalDateTime updationTime;
	
	public Event() {
		
	}
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public LocalDateTime getEventDate() {
		return eventDate;
	}
	public void setEventDate(LocalDateTime eventDate) {
		this.eventDate = eventDate;
	}
	public long getTotalSeats() {
		return totalSeats;
	}
	public void setTotalSeats(long totalSeats) {
		this.totalSeats = totalSeats;
	}
	
	
	
	
}
