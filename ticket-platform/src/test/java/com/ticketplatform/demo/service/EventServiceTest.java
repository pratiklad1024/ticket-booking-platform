package com.ticketplatform.demo.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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
		
		assertEquals(1L, response.id());
		assertEquals("Rock Concert", response.title());
		verify(eventRepository).save(any(Event.class));
	}
	
	@Test
	void findEventById_whenExists_returnResponse() {
		Event event = new Event();
		event.setId(2L);
		event.setTitle("Rock Concert");
		
		when(eventRepository.findById(2L)).thenReturn(Optional.of(event));
		
		EventResponse response = eventService.findEventById(1L);
		assertEquals("Rock Concert", response.title());
	}
	
	@Test
	void findEventById_whenMissing_throwsNotFound() {
		when(eventRepository.findById(1L)).thenReturn(Optional.empty());
		
		assertThrows(EventNotFoundException.class, () -> eventService.findEventById(1L));
		
	}
	
	@Test
	void findAllEvents_returnsMappedList() {
		Event event = new Event();
		
		event.setId(1L);
		event.setTitle("Rock Concert");
		
		when(eventRepository.findAll()).thenReturn(List.of(event));
		
		List<EventResponse> result = eventService.findAllEvents();
		
		assertEquals(1, result.size());
		assertEquals("Rock Concert", result.get(0).title());
	}
}
