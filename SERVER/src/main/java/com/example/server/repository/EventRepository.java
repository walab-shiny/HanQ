package com.example.server.repository;

import com.example.server.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event,Integer> {
    List<Event> findEventsByClosedIsFalse();
}
