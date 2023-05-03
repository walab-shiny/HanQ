package com.example.server.event;

import com.example.server.dto.EventCreateDto;
import com.example.server.dto.EventIdDto;
import com.example.server.dto.EventDto;
import com.example.server.dto.EventUpdateDto;
import com.example.server.repository.EventRepository;
import com.example.server.service.EventService;
import com.example.server.service.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EventTests {
    @Autowired
    EventService eventService;
    @Autowired
    UserService userService;
    @Autowired
    private EventRepository eventRepository;

    @Test
    @Order(1)
    public void createEvent() {
        String token = "106748212855095490148";
        List<Integer> tags = new ArrayList<>();
        tags.add(1);
        tags.add(2);
        EventDto dto = eventService.createEvent(new EventCreateDto("Private Event","2022-05-05T12:00:00","2022-05-05T12:00:00","평봉필드",500,2,"전산전자공학부 vs 경영경제뭐시기ㅋㅋ 어차피 전전이 이김",15,"",tags,false),token);
        System.out.println("dto = " + dto);
        assertThat(dto.getIsPublic()).isEqualTo(false);
        assertThat(dto.getAffiliation()).isEqualTo(userService.getUserByToken("106748212855095490148").getAffiliation());
//        assertThat(dto.getHost().getToken()).isEqualTo(token);
    }
    @Test
    public void deleteEvent() {
        String token = "1234567890";
        eventService.deleteEvent(new EventIdDto(1),token);
        eventService.deleteEvent(new EventIdDto(2),token);
        eventService.deleteEvent(new EventIdDto(3),token);
        eventService.deleteEvent(new EventIdDto(4),token);
        eventService.deleteEvent(new EventIdDto(5),token);
        eventService.getEvents(token).forEach(e -> System.out.println("e = " + e));
        assertThat(eventService.getEvents(token).size()).isEqualTo(2);
    }
    @Test
    public void readEvents() {
        String token = "1234567890";
        List<EventDto> list = eventService.getEvents(token);
        list.forEach(e -> System.out.println("e = " + e));
        assertThat(list.size()).isEqualTo(2);
    }
    @Test
    public void updateEvent() {
        List<Integer> tags = new ArrayList<>();
        tags.add(1);
        tags.add(2);
        tags.add(3);
        String token = "1234567890";
        eventService.updateEvent(new EventUpdateDto(6,"전전경경대전","2022-05-05T12:30:00","2022-05-05T12:30:00","평봉필드, 히딩크필드",600,2,"전산전자공학부 vs 경영경제뭐시기ㅋㅋ",10,"",tags,false),token);
        assertThat(eventService.getEvents(token).get(1).getName()).isEqualTo("전전경경대전");
        System.out.println("eventService = " + eventService.getEvents(token));
    }
    @Test
    public void getEvent() {
        String token = "1234567890";
        System.out.println("eventService = " + eventService.getEvent(6,token));
    }

    @Test
    public void getAllEvents() {
        List<EventDto> list = eventService.getAllEvents();
        assertThat(list.size()).isEqualTo(3);
        list.forEach(e -> System.out.println("e.getName() = " + e.getName()));
    }
}
