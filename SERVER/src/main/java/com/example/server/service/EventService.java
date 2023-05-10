package com.example.server.service;

import com.example.server.dto.*;
import com.example.server.entity.Attend;
import com.example.server.entity.Event;
import com.example.server.entity.Tag;
import com.example.server.entity.User;
import com.example.server.entity.relation.EventTag;
import com.example.server.entity.relation.UserTag;
import com.example.server.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
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
        List<Tag> tags = tagService.getTagsFromList(dto.getTags());
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
            List<Tag> tags = tagService.getTagsFromList(dto.getTags());
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
            EventDto dto = e.toDto(tags);
            dto.setCode(e.getAccessCode().getCode());
            return dto;
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

    public List<AttendedEventDto> getAttendedEvents(String token) {
        User user = userService.getUserByToken(token);
        List<Attend> attends = user.getAttends();
        List<LocalDateTime> dates = attends.stream().map(Attend::getTaggedAt).collect(Collectors.toList());
        List<Event> events = attends.stream().map(Attend::getEvent).collect(Collectors.toList());
        List<AttendedEventDto> result =  events.stream().map(e -> {
            List<EventTag> eventTags = eventTagService.getEventTagsByEventId(e.getId());
            List<Tag> tags = tagService.getTagsFromEventTag(eventTags);
            return e.toAttendedDto(tags);
        }).collect(Collectors.toList());
        for(int i=0;i<result.size();i++) {
            result.get(i).setTaggedTime(dates.get(i));
        }
        return result;
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
    @Scheduled(cron = "0 0 * * * *",zone = "Asia/Seoul")  // 배포할 때 시간 cron 으로 바꾸기
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
    public CheckEventPasswordDto checkPasswordAndCode(EventPasswordCheckDto dto) {
        CheckEventPasswordDto result = new CheckEventPasswordDto();
        Optional<Event> event = eventRepository.findEventByAccessCode_Code(dto.getCode());
        if(!event.isPresent())
            result.setResult(false);
        if(event.get().getAccessCode().getCode().equals(dto.getCode())) {
            if(event.get().getPassword().equals(DigestUtils.sha256Hex(dto.getPassword()))) {
                result.setResult(true);
                result.setId(event.get().getId());
            }
        }
        return result;
    }
    @Transactional
    public EventCountDto eventTotalCount() {
        return new EventCountDto(eventRepository.findAll().size());
    }

    @Transactional
    public List<EventDto> getLikedEvents(String token) {
        User user = userService.getUserByToken(token);
        List<Tag> tagList = user.getTags().stream().map(UserTag::getTag).collect(Collectors.toList());
        System.out.println("tagList = " + tagList);
        List<Event> temp = new ArrayList<>();
//        tagList.stream().map(tag -> temp.addAll(eventTagService.getEventTagsByTagId(tag.getId()).stream().map(EventTag::getEvent).collect(Collectors.toList())));
        tagList.forEach(t -> {
            temp.addAll(t.getEvents().stream().map(EventTag::getEvent).collect(Collectors.toList()));
        });
        List<Event> events = new ArrayList<>(new HashSet<>(temp));
        return events.stream().map(e -> {
            List<EventTag> eventTags = eventTagService.getEventTagsByEventId(e.getId());
            List<Tag> tags = tagService.getTagsFromEventTag(eventTags);
            return e.toDto(tags);
        }).collect(Collectors.toList());
    }
}
