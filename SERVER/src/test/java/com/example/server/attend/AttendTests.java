package com.example.server.attend;

import com.example.server.dto.AttendMonthlyCountRequest;
import com.example.server.entity.Attend;
import com.example.server.repository.AttendRepository;
import com.example.server.service.AttendService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
public class AttendTests {
    @Autowired
    AttendService attendService;
    @Autowired
    AttendRepository attendRepository;
    @Test
    @DisplayName("날짜 사이 개수 테스트")
    public void countMonthly() {
        int a = attendService.countAttendMonthly(new AttendMonthlyCountRequest("2023-04-02T12:00:00")).getCount();
        int b = attendService.countAttendMonthly(new AttendMonthlyCountRequest("2023-05-02T12:00:00")).getCount();
        int c = attendService.countAttendMonthly(new AttendMonthlyCountRequest("2023-06-02T12:00:00")).getCount();
        assertThat(a).isEqualTo(2);
        assertThat(b).isEqualTo(1);
        assertThat(c).isEqualTo(0);
    }
    @Test
    @DisplayName("페이징 후 개수 테스트")
    public void countPageUsers() {
        Pageable pageable = PageRequest.of(0,20);
        Page<Attend> attendPage = attendRepository.getDistinctByEventId(117,pageable);
        assertThat(attendPage.stream().count()).isEqualTo(20);
    }
}
