package com.example.server.service;

import com.example.server.entity.Event;
import com.example.server.entity.Tag;
import com.example.server.entity.relation.EventTag;
import com.example.server.repository.EventRepository;
import com.example.server.repository.EventTagRepository;
import com.example.server.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventTagService {
    private final EventTagRepository eventTagRepository;
    private final EventRepository eventRepository;
    private final TagRepository tagRepository;

    @Transactional
    public List<EventTag> createRelation(List<Tag> tags, Event event) {
        tags.forEach(t -> {
            EventTag eventTag = new EventTag();
            eventTag.setEvent(event);
            eventTag.setTag(t);
            EventTag saved = eventTagRepository.save(eventTag);
            t.getEvents().add(saved);
            event.getTags().add(saved);
        });
        return eventTagRepository.findEventTagByEvent_Id(event.getId());
    }
    public void deleteRelations(int id) {
        eventTagRepository.deleteAllByEvent_Id(id);
    }
    public List<EventTag> getEventTagsByEventId(int id) {
        return eventTagRepository.findEventTagByEvent_Id(id);
    }
    public List<EventTag> getEventTagsByTagId(int id) {
        return eventTagRepository.findEventTagsByTag_Id(id);
    }
}
