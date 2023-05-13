package com.example.server.service;

import com.example.server.dto.CreateReportDto;
import com.example.server.dto.EventIdDto;
import com.example.server.dto.ReportDto;
import com.example.server.dto.UpdateReportDto;
import com.example.server.entity.Event;
import com.example.server.entity.Report;
import com.example.server.entity.User;
import com.example.server.repository.EventRepository;
import com.example.server.repository.ReportRepository;
import com.example.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    @Transactional
    public ReportDto createReport(CreateReportDto dto, String token) {
        Report report = new Report();
        report.setContent(dto.getContent());
        Report saved = reportRepository.save(report);
        User user = userRepository.findUserByToken(token);
        Event event = eventRepository.findById(dto.getEventId()).orElseThrow();
        saved.setUser(user);
        saved.setEvent(event);
        return saved.toDto();
    }
    @Transactional
    public ReportDto updateReport(UpdateReportDto dto, String token) {
        Report report = reportRepository.findById(dto.getId()).orElseThrow();
        report.setContent(dto.getContent());
        ReportDto result = report.toDto();
        result.setModifiedAt(LocalDateTime.now(ZoneId.of("Asia/Seoul")));
        return result;
    }

    @Transactional
    public List<ReportDto> getEventReports(int id){
        return reportRepository.getReportsByEvent_Id(id).stream().map(Report::toDto).collect(Collectors.toList());
    }
    @Transactional
    public List<ReportDto> getUserReports(String token){
        return reportRepository.getReportsByUser_Token(token).stream().map(Report::toDto).collect(Collectors.toList());
    }

}
