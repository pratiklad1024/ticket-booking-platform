package com.ticketplatform.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ticketplatform.demo.dto.EventRequest;
import com.ticketplatform.demo.dto.EventResponse;
import com.ticketplatform.demo.entity.Event;
import com.ticketplatform.demo.repository.EventRepository;

@Service
public class EventService {

	private EventRepository eventRepository;

	public EventService(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}
	
	@Transactional
	public EventResponse addEvent(EventRequest event) {
		return toResponse(eventRepository.save(toEntity(event)));
	}
	
	@Transactional(readOnly = true)
	public List<EventResponse> findAllEvents(){
		return eventRepository.findAll().stream()
							  .map(this :: toResponse)
							  .toList();
	}
	
	@Transactional(readOnly = true)
	public EventResponse findEventById(Long id) {
		Event event = eventRepository.findById(id)
					.orElseThrow(() -> new RuntimeException("Event not found with id "+ id));//Optional<T>
			return toResponse(event);
	}
	
	private Event toEntity(EventRequest eventRequest) {
		Event event = new Event();
		event.setTitle(eventRequest.title());
		event.setDescription(eventRequest.description());
		event.setVenue(eventRequest.venue());
		event.setTotalSeats(eventRequest.totalSeats());
		event.setEventDate(eventRequest.eventDate());
		return event;
	}
	
	private EventResponse toResponse(Event event) {
		return new EventResponse(event.getId(), event.getTitle(), event.getDescription(),
				event.getVenue(), event.getEventDate(), event.getTotalSeats());
	}
	
	
}
