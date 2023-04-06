package com.example.server.controller;

import com.example.server.dto.EventCreateDto;
import com.example.server.dto.EventDto;
import com.example.server.dto.EventIdDto;
import com.example.server.dto.EventUpdateDto;
import com.example.server.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/event")
public class EventController {
    private final EventService eventService;

    @PostMapping
    public ResponseEntity<EventDto> createEvent(@RequestBody EventCreateDto dto, @RequestHeader(name = "Authorization") String token) {
        return ResponseEntity.ok(eventService.createEvent(dto,token));
    }
    @GetMapping("/list")
    public ResponseEntity<List<EventDto>> getEvents(@RequestHeader(name = "Authorization") String token) {
        return ResponseEntity.ok(eventService.getEvents(token));
    }
    @GetMapping
    public ResponseEntity<EventDto> getEvent(@RequestBody EventIdDto dto, @RequestHeader(name = "Authorization") String token) {
        return ResponseEntity.ok(eventService.getEvent(dto,token));
    }

    @PostMapping("/update")
    public ResponseEntity<EventDto> updateEvent(@RequestBody EventUpdateDto dto, @RequestHeader(name = "Authorization") String token) {
        return ResponseEntity.ok(eventService.updateEvent(dto,token));
    }

    @PostMapping("/delete")
    public ResponseEntity<Integer> deleteEvent(@RequestBody EventIdDto dto, @RequestHeader(name = "Authorization") String token) {
        return ResponseEntity.ok(eventService.deleteEvent(dto, token));
    }

}
