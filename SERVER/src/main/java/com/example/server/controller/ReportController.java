package com.example.server.controller;

import com.example.server.dto.CreateReportDto;
import com.example.server.dto.ReportDto;
import com.example.server.dto.UpdateReportDto;
import com.example.server.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/api/report")
public class ReportController {
    private final ReportService reportService;
    @PostMapping
    public ResponseEntity<ReportDto> createReport(@RequestBody CreateReportDto dto, @RequestHeader(name = "Authorization") String token) {
        return ResponseEntity.ok(reportService.createReport(dto, token));
    }
    @PostMapping("/update")
    public ResponseEntity<ReportDto> updateReport(@RequestBody UpdateReportDto dto, @RequestHeader(name = "Authorization") String token) {
        return ResponseEntity.ok(reportService.updateReport(dto, token));
    }
    @GetMapping("/event/{id}")
    public ResponseEntity<List<ReportDto>> getEventReports(@PathVariable int id) {
        return ResponseEntity.ok(reportService.getEventReports(id));
    }
    @GetMapping("/user")
    public ResponseEntity<List<ReportDto>> getUserReports(@RequestHeader(name = "Authorization") String token) {
        return ResponseEntity.ok(reportService.getUserReports(token));
    }
}
