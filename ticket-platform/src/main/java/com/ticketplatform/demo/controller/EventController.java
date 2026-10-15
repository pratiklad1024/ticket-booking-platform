package com.ticketplatform.demo.controller;



import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	public ResponseEntity<List<EventResponse>> findAllEvents(){
		List<EventResponse> allEvents = eventService.findAllEvents();
		return new ResponseEntity<>(allEvents, HttpStatus.OK);
	}
	
	
}
