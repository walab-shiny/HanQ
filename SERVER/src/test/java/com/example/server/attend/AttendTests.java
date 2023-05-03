package com.example.server.attend;

import com.example.server.dto.AttendMonthlyCountRequest;
import com.example.server.service.AttendService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
public class AttendTests {
    @Autowired
    AttendService attendService;
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
}
