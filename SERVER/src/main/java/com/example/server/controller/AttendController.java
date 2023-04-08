package com.example.server.controller;

import com.example.server.dto.QrResponseDto;
import com.example.server.dto.QrStringDto;
import com.example.server.service.AttendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/attend")
public class AttendController {
    private final AttendService attendService;
    @PostMapping
    public ResponseEntity<QrResponseDto> createAttend(@RequestBody QrStringDto dto) throws Exception {
        return ResponseEntity.ok(attendService.createAttend(dto));
    }
}
