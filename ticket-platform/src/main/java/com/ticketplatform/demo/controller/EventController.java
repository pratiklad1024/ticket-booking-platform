package com.ticketplatform.demo.controller;



import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticketplatform.demo.entity.Event;
import com.ticketplatform.demo.service.EventService;

@RestController
@RequestMapping("/api/events")
public class EventController {

	private EventService eventService;

	public EventController(EventService eventService) {
		this.eventService = eventService;
	}
	
	@PostMapping
	public ResponseEntity<Event> createEvent(@RequestBody Event event){
		Event createEvent = eventService.addEvent(event);
		return new ResponseEntity<>(createEvent, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Event> findEventById(@PathVariable Long id){
		Event findEvent = eventService.findEventById(id);
		return new ResponseEntity<Event>(findEvent, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Event>> findAllEvents(){
		List<Event> allEvents = eventService.findAllEvents();
		return new ResponseEntity<List<Event>>(allEvents, HttpStatus.OK);
	}
	
	
}
