package com.example.server.controller;

import com.example.server.dto.*;
import com.example.server.qr.Result;
import com.example.server.service.AttendService;
import com.example.server.service.EventService;
import com.example.server.service.UserService;
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
    @PostMapping("/memo")
    public ResponseEntity<AttendUserDto> createMemo(@RequestBody MemoCreateDto dto, @RequestHeader(name = "Authorization") String token) {
        return ResponseEntity.ok(attendService.addMemo(dto, token));
    }

    @PostMapping("/memo/delete")
    public ResponseEntity<AttendUserDto> deleteMemo(@RequestBody MemoDeleteDto dto, @RequestHeader(name = "Authorization") String token) {
        return ResponseEntity.ok(attendService.deleteMemo(dto, token));
    }

    @PostMapping("/memo/update")
    public ResponseEntity<AttendUserDto> updateMemo(@RequestBody MemoCreateDto dto, @RequestHeader(name = "Authorization") String token) {
        return ResponseEntity.ok(attendService.addMemo(dto,token));
    }
    @PostMapping("/monthly")
    public ResponseEntity<AttendMonthlyCountResponse> monthlyCount(@RequestBody AttendMonthlyCountRequest request) {
        return ResponseEntity.ok(attendService.countAttendMonthly(request));
    }
}
