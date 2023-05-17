package com.example.server.controller;

import com.example.server.dto.*;
import com.example.server.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.relational.core.sql.In;
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
    // User created events
    @GetMapping("/list")
    public ResponseEntity<List<EventDto>> getEvents(@RequestHeader(name = "Authorization") String token) {
        return ResponseEntity.ok(eventService.getEvents(token));
    }
    @GetMapping("/list/all")
    public ResponseEntity<List<EventDto>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }
    @GetMapping("/attended")
    public ResponseEntity<List<AttendedEventDto>> getAttendedEvents(@RequestHeader(name = "Authorization") String token) {
        return ResponseEntity.ok(eventService.getAttendedEvents(token));
    }
    @GetMapping("/list/hot")
    public ResponseEntity<List<EventDto>> getHotEvents() {
        return ResponseEntity.ok(eventService.getHotEvents());
    }
    @GetMapping("/{id}")
    public ResponseEntity<EventDto> getEvent(@PathVariable(name = "id") int id,@RequestHeader(name = "Authorization") String token) {
        return ResponseEntity.ok(eventService.getEvent(id, token));
    }

    @PostMapping("/update")
    public ResponseEntity<EventDto> updateEvent(@RequestBody EventUpdateDto dto, @RequestHeader(name = "Authorization") String token) {
        return ResponseEntity.ok(eventService.updateEvent(dto,token));
    }

    @PostMapping("/delete")
    public ResponseEntity<Integer> deleteEvent(@RequestBody EventIdDto dto, @RequestHeader(name = "Authorization") String token) {
        return ResponseEntity.ok(eventService.deleteEvent(dto, token));
    }
    @PostMapping("/close")
    public ResponseEntity<EventDto> closeEvent(@RequestBody EventIdDto dto, @RequestHeader(name = "Authorization") String token) {
        return ResponseEntity.ok(eventService.closeEvent(dto,token));
    }
    @PostMapping("/password")
    public ResponseEntity<Integer> setEventPassword(@RequestBody EventPasswordDto dto, @RequestHeader(name = "Authorization") String token) {
        return ResponseEntity.ok(eventService.setPassword(dto, token));
    }
    @PostMapping("/check")
    public ResponseEntity<CheckEventPasswordDto> checkEventPassword(@RequestBody EventPasswordCheckDto dto) {
        return ResponseEntity.ok(eventService.checkPasswordAndCode(dto));
    }
    @GetMapping("/count")
    public ResponseEntity<EventCountDto> eventTotalCount() {
        return ResponseEntity.ok(eventService.eventTotalCount());
    }

    @GetMapping("/tagged")
    public ResponseEntity<List<EventDto>> getTagged(@RequestHeader(name = "Authorization") String token) {
        return ResponseEntity.ok(eventService.getLikedEvents(token));
    }
    @GetMapping("/views/{id}")
    public ResponseEntity<?> increaseViews(@PathVariable int id) {
        eventService.increaseViews(id);
        return ResponseEntity.noContent().build();
    }
}
