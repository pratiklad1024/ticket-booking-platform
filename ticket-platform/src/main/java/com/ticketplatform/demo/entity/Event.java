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


@Entity
@Table(name = "events")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	
	private String title;
	
	private String description;
	
	private String venue;
	@Column(name = "event_date")
	
	private LocalDateTime eventDate;
	
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
