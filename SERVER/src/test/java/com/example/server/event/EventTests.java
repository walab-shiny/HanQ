package com.example.server.event;

import com.example.server.dto.*;
import com.example.server.entity.Event;
import com.example.server.repository.AccessCodeRepository;
import com.example.server.repository.EventRepository;
import com.example.server.service.AccessCodeService;
import com.example.server.service.EventService;
import com.example.server.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
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
    EventRepository eventRepository;
    @Autowired
    AccessCodeService accessCodeService;
    @Autowired
    AccessCodeRepository accessCodeRepository;

    @Test
    @Order(1)
    public void createEvent() {
        String token = "106748212855095490148";
        List<Integer> tags = new ArrayList<>();
        tags.add(1);
        tags.add(2);
        EventDto dto = eventService.createEvent(new EventCreateDto("Private Event", "2022-05-05T12:00:00", "2022-05-05T12:00:00", "평봉필드", 500, 2, "전산전자공학부 vs 경영경제뭐시기ㅋㅋ 어차피 전전이 이김", 15, "", tags, false), token);
        System.out.println("dto = " + dto);
        assertThat(dto.getIsPublic()).isEqualTo(false);
        assertThat(dto.getAffiliation()).isEqualTo(userService.getUserByToken("106748212855095490148").getAffiliation());
//        assertThat(dto.getHost().getToken()).isEqualTo(token);
    }

    @Test
    public void deleteEvent() {
        String token = "1234567890";
        eventService.deleteEvent(new EventIdDto(1), token);
        eventService.deleteEvent(new EventIdDto(2), token);
        eventService.deleteEvent(new EventIdDto(3), token);
        eventService.deleteEvent(new EventIdDto(4), token);
        eventService.deleteEvent(new EventIdDto(5), token);
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
        String token = "106748212855095490148";
        eventService.updateEvent(new EventUpdateDto(5, "전전경경대전 Private", "2022-05-05T12:30:00", "2022-05-05T12:30:00", "평봉필드, 히딩크필드", 600, 2, "test", 10, "", tags, true), token);
        System.out.println("eventService = " + eventService.getEvents(token));
        assertThat(eventService.getEvents(token).get(1).getName()).isEqualTo("전전경경대전 Private");
        assertThat(eventService.getEvents(token).get(1).getIsPublic()).isEqualTo(true);
    }
//    @Test
//    public void getEvent() {
//        System.out.println("eventService = " + eventService.getEvent(6));
//    }

    @Test
    public void getAllEvents() {
        List<EventDto> list = eventService.getAllEvents();
        assertThat(list.size()).isEqualTo(3);
        list.forEach(e -> System.out.println("e.getName() = " + e.getName()));
    }

//    @Test
//    public void affiliationTest() {
//        System.out.println("eventService = " + eventService.getEvent(5));
//    }

    //    @Test
//    @DisplayName("조회수 확인하기")
//    public void viewsTest() {
//        EventDto dto = eventService.getEvent(4);
//        Event event = eventRepository.findById(4).orElseThrow();
//        System.out.println("event = " + dto);
//        assertThat(dto.getViews()).isEqualTo(event.getViews());
//        assertThat(event.getViews()).isEqualTo(2);
//    }
    @Test
    @DisplayName("랜덤 스트링 생성 및 이벤트 연관관계 설정")
    public void randomStringTest() {
        String token = "106748212855095490148";
        List<Integer> tags = new ArrayList<>();
        tags.add(1);
        tags.add(2);
        EventDto dto = eventService.createEvent(new EventCreateDto("Random String Test", "2022-05-05T12:00:00", "2022-05-05T12:00:00", "평봉필드", 500, 2, "전산전자공학부 vs 경영경제뭐시기ㅋㅋ 어차피 전전이 이김", 15, "", tags, false), token);
        System.out.println("dto = " + dto);
        System.out.println("accessCodeRepository = " + accessCodeRepository.findById(1).orElseThrow().toDto());
    }

    @Test
    @DisplayName("연관관계 확인")
    public void accessCodeEventRelationTest() {
        assertThat(accessCodeRepository.findById(1).orElseThrow().getEvent().getId()).isEqualTo(6);
        assertThat(eventRepository.findById(6).orElseThrow().getAccessCode().getId()).isEqualTo(1);
    }

    @Test
    @DisplayName("비밀번호 설정")
    public void setPasswordTest() {
        eventService.setPassword(new EventPasswordDto(6, "ime2me"), "106748212855095490148");
        Event event = eventRepository.findById(6).orElseThrow();
        assertThat(event.getPassword()).isEqualTo(DigestUtils.sha256Hex("ime2me"));
    }

    @Test
    @DisplayName("코드와 비밀번호 확인")
    public void checkPasswordTest() {
        CheckEventPasswordDto a = eventService.checkPasswordAndCode(new EventPasswordCheckDto("ROVDxD", "ime2me"));
        CheckEventPasswordDto b = eventService.checkPasswordAndCode(new EventPasswordCheckDto("ROVDxd", "ime2me"));
        CheckEventPasswordDto c = eventService.checkPasswordAndCode(new EventPasswordCheckDto("ROVDxD", "ime2m2"));
        assertThat(a.isResult()).isEqualTo(Boolean.TRUE);
        assertThat(b.isResult()).isEqualTo(Boolean.FALSE);
        assertThat(c.isResult()).isEqualTo(Boolean.FALSE);
    }

    @Test
    @DisplayName("좋아한 태그 이벤트 뽑기")
    public void likedTagEventsTest() {
        List<EventDto> list = eventService.getLikedEvents("83247847347");
        assertThat(list.size()).isEqualTo(4);
        System.out.println("list = " + list);
    }
}
