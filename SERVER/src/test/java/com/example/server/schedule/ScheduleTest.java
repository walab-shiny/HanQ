package com.example.server.schedule;

import com.example.server.schedule.config.ScheduledConfig;
import com.example.server.service.EventService;
import com.example.server.service.HostAuthRequestService;
import com.example.server.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringJUnitConfig(ScheduledConfig.class)
public class ScheduleTest {

    @Autowired
    EventService eventService;
    @Autowired
    UserService userService;

    @Test
    @DisplayName("이벤트 기한 밖이면 닫기")
    public void eventSchedule() throws InterruptedException {
       Thread.sleep(6000);

       assertThat(eventService.getEvent(1,"83247847347").getClosed()).isEqualTo(true);
        assertThat(eventService.getEvent(2,"83247847347").getClosed()).isEqualTo(true);
    }

    @Test
    @DisplayName("권한 마감기한 지나면 권한 뺏기")
    public void hostSchedule() throws InterruptedException {
        Thread.sleep(5000);
        assertThat(userService.getUser(1).getIsHost()).isEqualTo(false);
    }
}
