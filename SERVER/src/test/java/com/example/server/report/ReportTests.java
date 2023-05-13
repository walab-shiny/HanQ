package com.example.server.report;

import com.example.server.dto.CreateReportDto;
import com.example.server.dto.EventIdDto;
import com.example.server.dto.ReportDto;
import com.example.server.dto.UpdateReportDto;
import com.example.server.service.ReportService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
public class ReportTests {
    @Autowired
    ReportService reportService;

    @Test
    @DisplayName("소감문 생성 테스트")
    public void createReportTest() {
        reportService.createReport(new CreateReportDto(100, "잘 들었습니다"), "112162645752275142872");
//        List<ReportDto> eList = reportService.getEventReports(9);
//        List<ReportDto> uList = reportService.getUserReports("112162645752275142872");
//        assertThat(eList.size()).isEqualTo(1);
//        assertThat(uList.size()).isEqualTo(1);
//        assertThat(eList.get(0).getStudentNum()).isEqualTo(22000328L);
//        assertThat(eList.get(0).getName()).isEqualTo("배주영");
//        assertThat(uList.get(0).getStudentNum()).isEqualTo(22000328L);
//        assertThat(uList.get(0).getName()).isEqualTo("배주영");
    }
    @Test
    @DisplayName("소감문 수정 테스트")
    public void updateReportTest() {
        List<ReportDto> eList = reportService.getEventReports(9);
        LocalDateTime a = eList.get(0).getModifiedAt();
        ReportDto dto = reportService.updateReport(new UpdateReportDto(1,"잘...ㅋ"),"112162645752275142872");
        List<ReportDto> eList2 = reportService.getEventReports(9);
        LocalDateTime b = eList2.get(0).getModifiedAt();
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("dto.getModifiedAt() = " + dto.getModifiedAt());
    }
}
