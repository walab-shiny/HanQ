package com.example.server.service;

import com.example.server.dto.*;
import com.example.server.entity.Attend;
import com.example.server.entity.Event;
import com.example.server.entity.Tag;
import com.example.server.entity.User;
import com.example.server.entity.relation.EventTag;
import com.example.server.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final TagService tagService;
    private final UserService userService;
    private final EventTagService eventTagService;
    private final AccessCodeService accessCodeService;

    @Transactional
    public EventDto createEvent(EventCreateDto dto, String token) {
        User host = userService.getUserByToken(token);
        List<Tag> tags = tagService.getTagsFromEvent(dto.getTags());
//        Event event = new Event(dto.getName(),LocalDateTime.parse(dto.getOpenAt()),LocalDateTime.parse(dto.getCloseAt()),host,dto.getLocation(),dto.getMaxUsers(),dto.getReportTimeLimit(),dto.getContent(),dto.getAvailableTime(),dto.getImage());
        Event event = new Event(dto);
        Event saved = eventRepository.save(event);
        List<EventTag> relations = eventTagService.createRelation(tags,saved);
        saved.setTags(relations);
        saved.setHost(host);
        saved.setAffiliation();
        accessCodeService.createAccessCode(saved.getId());
        return saved.toDto(tags);
    }
    @Transactional
    public int deleteEvent(EventIdDto dto, String token) {
        User host = userService.getUserByToken(token);
        int deleted = eventRepository.findById(dto.getId()).orElseThrow().getId();
        if(host.getIsHost()) {
            eventTagService.deleteRelations(dto.getId());
            eventRepository.deleteById(dto.getId());
        }
        return deleted;
    }
    @Transactional
    public EventDto updateEvent(EventUpdateDto dto, String token) {
        User host = userService.getUserByToken(token);
        if(host.getIsHost()) {
            Event updated = eventRepository.findById(dto.getId()).orElseThrow();
            System.out.println("updated = " + updated);
            List<Tag> tags = tagService.getTagsFromEvent(dto.getTags());
            eventTagService.deleteRelations(dto.getId());
            List<EventTag> relations = eventTagService.createRelation(tags, updated);
            updated.update(dto);
            updated.setTags(relations);
            return updated.toDto(tags);
        }
        return null;
    }
    @Transactional
    public List<EventDto> getEvents(String token) {
        User host = userService.getUserByToken(token);
        return host.getEvents().stream().map(e -> {
            List<EventTag> eventTags = eventTagService.getEventTagsByEventId(e.getId());
            List<Tag> tags = tagService.getTagsFromEventTag(eventTags);
            return e.toDto(tags);
        }).collect(Collectors.toList());
    }
    @Transactional
    public EventDto getEvent(int id,String token) {
        User host = userService.getUserByToken(token);
        Event event = eventRepository.findById(id).orElseThrow();
        event.incrementViews();
        EventDto dto = event.toDto(tagService.getTagsFromEventTag(eventTagService.getEventTagsByEventId(id)));
        if(host.isHostFor(id))
            dto.setCode(event.getAccessCode().getCode());
        return dto;
    }

    public List<EventDto> getAttendedEvents(String token) {
        User user = userService.getUserByToken(token);
        List<Attend> attends = user.getAttends();
        List<Event> events = attends.stream().map(Attend::getEvent).collect(Collectors.toList());
        return events.stream().map(e -> {
            List<EventTag> eventTags = eventTagService.getEventTagsByEventId(e.getId());
            List<Tag> tags = tagService.getTagsFromEventTag(eventTags);
            return e.toDto(tags);
        }).collect(Collectors.toList());
    }
    public List<EventDto> getAllEvents() {
        List<Event> events = eventRepository.findEventsByClosedIsFalseAndIsPublicIsTrue();
        return events.stream().map(e -> {
            List<EventTag> eventTags = eventTagService.getEventTagsByEventId(e.getId());
            List<Tag> tags = tagService.getTagsFromEventTag(eventTags);
            return e.toDto(tags);
        }).collect(Collectors.toList());
    }
    @Transactional
    public EventDto closeEvent(EventIdDto dto, String token) {
        User host = userService.getUserByToken(token);
        if(host.getIsHost()) {
            Event event = eventRepository.findById(dto.getId()).orElseThrow();
            event.close();
            List<EventTag> eventTags = eventTagService.getEventTagsByEventId(dto.getId());
            List<Tag> tags = tagService.getTagsFromEventTag(eventTags);
            return event.toDto(tags);
        }
        return null;
    }

    @Transactional
    @Scheduled(fixedDelay = 60000)  // 배포할 때 시간 cron 으로 바꾸기
    public void closeEventScheduled() {
        List<Event> events = eventRepository.findEventsByClosedIsFalse();
        events.forEach(e -> {
                if(e.getCloseAt().isBefore(LocalDateTime.now()))
                    e.close();

        });
    }

    @Transactional
    public int setPassword(EventPasswordDto dto,String token) {
        User host = userService.getUserByToken(token);
        if(host.isHostFor(dto.getId())) {
            Event event = eventRepository.findById(dto.getId()).orElseThrow();
            String encrypted = DigestUtils.sha256Hex(dto.getPassword());
            event.setPassword(encrypted);
            return 1;
        }
        return 0;
    }

    @Transactional
    public Boolean checkPasswordAndCode(EventPasswordCheckDto dto) {
        Optional<Event> event = eventRepository.findEventByAccessCode_Code(dto.getCode());
        if(!event.isPresent())
            return false;
        if(event.get().getAccessCode().getCode().equals(dto.getCode())) {
            if(event.get().getPassword().equals(DigestUtils.sha256Hex(dto.getPassword())))
                return true;
        }
        return false;
    }
}
