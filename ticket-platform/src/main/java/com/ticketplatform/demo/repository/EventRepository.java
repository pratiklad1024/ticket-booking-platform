package com.ticketplatform.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ticketplatform.demo.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long>{

}
