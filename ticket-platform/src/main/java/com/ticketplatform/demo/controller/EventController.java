package com.ticketplatform.demo.controller;




import java.util.List;

import org.hibernate.query.SortDirection;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticketplatform.demo.dto.EventRequest;
import com.ticketplatform.demo.dto.EventResponse;
import com.ticketplatform.demo.service.EventService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/events")
public class EventController {

	private final EventService eventService;

	public EventController(EventService eventService) {
		this.eventService = eventService;
	}
	
	@PostMapping
	public ResponseEntity<EventResponse> createEvent(@Valid @RequestBody EventRequest event){
		EventResponse createEvent = eventService.addEvent(event);
		return new ResponseEntity<>(createEvent, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EventResponse> findEventById(@PathVariable Long id){
		EventResponse findEvent = eventService.findEventById(id);
		return new ResponseEntity<>(findEvent, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<Page<EventResponse>> findAllEvents
		(@ParameterObject @PageableDefault(size=10, sort="eventDate", direction = Sort.Direction.ASC) Pageable pageable){
		Page<EventResponse> allEvents = eventService.findAllEvents(pageable);
		return new ResponseEntity<>(allEvents, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EventResponse> updateEvent(@PathVariable Long id, @Valid @RequestBody EventRequest eventRequest){
		EventResponse response = eventService.updateEvent(id, eventRequest);
		return ResponseEntity.ok(response);
	}  
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEvent(@PathVariable Long id){
		eventService.deleteEvent(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
