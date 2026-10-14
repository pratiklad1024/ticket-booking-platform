package com.ticketplatform.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ticketplatform.demo.entity.Event;
import com.ticketplatform.demo.repository.EventRepository;

@Service
public class EventService {

	private EventRepository eventRepository;

	public EventService(EventRepository eventRepository) {
		System.out.println("Event service constructor");
		this.eventRepository = eventRepository;
	}
	
	public Event addEvent(Event event) {
		return eventRepository.save(event);
	}
	
	public List<Event> findAllEvents(){
		return eventRepository.findAll();
	}
	
	public Event findEventById(Long id) {
		return eventRepository.findById(id).get();//Optional<T>
	}
	
	
	
	
}
