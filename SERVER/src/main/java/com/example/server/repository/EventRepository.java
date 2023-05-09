package com.example.server.repository;

import com.example.server.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event,Integer> {
    List<Event> findEventsByClosedIsFalse();
    List<Event> findEventsByClosedIsFalseAndIsPublicIsTrue();
    Optional<Event> findEventByAccessCode_Code(String code);
}
