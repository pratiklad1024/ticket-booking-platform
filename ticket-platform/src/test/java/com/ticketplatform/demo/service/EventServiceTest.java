package com.ticketplatform.demo.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springdoc.core.converters.models.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.ticketplatform.demo.dto.EventRequest;
import com.ticketplatform.demo.dto.EventResponse;
import com.ticketplatform.demo.entity.Event;
import com.ticketplatform.demo.exception.EventNotFoundException;
import com.ticketplatform.demo.repository.EventRepository;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

	@Mock
	private EventRepository eventRepository;
	
	@InjectMocks
	private EventService eventService;
	
	@Test
	void addEvent_savesAndReturnsResponse() {
		EventRequest request = 
				new EventRequest("Rock Concert", "Live show", "City Arena", LocalDateTime.now().plusDays(10), 200);
		
		Event saved = new Event();
		saved.setId(2L);
		saved.setTitle("Rock Concert");
		saved.setVenue("City Arena");
		saved.setTotalSeats(200);
		
		when(eventRepository.save(any(Event.class))).thenReturn(saved);
		
		EventResponse response = eventService.addEvent(request);
		
		assertEquals(2L, response.id());
		assertEquals("Rock Concert", response.title());
		verify(eventRepository).save(any(Event.class));
	}
	
	@Test
	void findEventById_whenExists_returnResponse() {
		Event event = new Event();
		event.setId(2L);
		event.setTitle("Rock Concert");
		
		when(eventRepository.findById(2L)).thenReturn(Optional.of(event));
		
		EventResponse response = eventService.findEventById(2L);
		assertEquals("Rock Concert", response.title());
	}
	
	@Test
	void findEventById_whenMissing_throwsNotFound() {
		when(eventRepository.findById(1L)).thenReturn(Optional.empty());
		
		assertThrows(EventNotFoundException.class, () -> eventService.findEventById(1L));
		
	}
	
	@Test
	void findAllEvents_returnsMappedList() {
		PageRequest pageable = PageRequest.of(0, 10);
		Event event = new Event();
		
		event.setId(1L);
		event.setTitle("Rock Concert");
		
		Event event1 = new Event();
		
		event1.setId(1L);
		event1.setTitle("Jazz Night");
		
		when(eventRepository.findAll(pageable)).thenReturn( new PageImpl<>(List.of(event, event1), pageable, 10));
		
		
		Page<EventResponse> pages=  eventService.findAllEvents(pageable);
		
		List<EventResponse> events = pages.getContent();
		
		assertEquals(10, pages.getTotalElements());
		assertEquals(2, events.size());
		assertEquals("Rock Concert", events.get(0).title());
		assertEquals("Jazz Night", events.get(1).title());
	}
	
	
	
	@Test
	void updateEvent_whenExists_updateFields() {
		Long id = 3L;
		EventRequest request = new EventRequest("Super Bowl", "Football League", "Arena", LocalDateTime.now().plusDays(23), 88);
		Event event = new Event();
		event.setId(3L);
		event.setTitle("Bruno Mars Concert");
		event.setVenue("City Arena");
		event.setTotalSeats(200);
		
		when(eventRepository.findById(id)).thenReturn(Optional.of(event));
		
	    EventResponse response = eventService.updateEvent(id, request);
	   
	    assertEquals(response.id(), id);
	    assertEquals(response.title(), request.title());
	    assertEquals(response.description(), request.description());
	    assertEquals(response.venue(), request.venue());
	    assertEquals(response.totalSeats(), request.totalSeats());
	}
	
	@Test
	void updateEvent_whenMissing_throwsNotFound() {
		Long id = 3L;
		EventRequest request = new EventRequest("Super Bowl", "Football League", "Arena", LocalDateTime.now().plusDays(23), 88);
		
		
		when(eventRepository.findById(id)).thenReturn(Optional.empty());
		
		
		assertThrows(EventNotFoundException.class, () -> eventService.updateEvent(id, request));
		verify(eventRepository, never()).save(any());
		verify(eventRepository).findById(id);
	}
	
	@Test
	void deleteEvent_whenExists_callsDelete() {
		Event event = new Event();
		event.setId(2L);
		event.setTitle("Bruno Mars Concert");
		event.setVenue("City Arena");
		event.setTotalSeats(200);
		
		when(eventRepository.findById(2L)).thenReturn(Optional.of(event));
		
		eventService.deleteEvent(2L);
		verify(eventRepository).delete(event);
	}
	
	@Test
	void deleteEvent_whenMissing_throwsNotFound() {
		
		when(eventRepository.findById(2L)).thenReturn(Optional.empty());
		
		assertThrows(EventNotFoundException.class, () -> eventService.deleteEvent(2L));
		verify(eventRepository, never()).delete(any(Event.class));
	}
	
}
