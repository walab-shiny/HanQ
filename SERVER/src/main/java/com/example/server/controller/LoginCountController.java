package com.example.server.controller;

import com.example.server.dto.LoginCountResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/login/count")
@Slf4j
public class LoginCountController {
    private int yesterday=0;
    private int today=0;
    @GetMapping
    public ResponseEntity<LoginCountResponseDto> getLoginCount() {
        LoginCountResponseDto dto = new LoginCountResponseDto(this.yesterday, this.today);
        return ResponseEntity.ok(dto);
    }

    public void incrementLoginCount() {
        this.today++;
        log.info(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + ": Login count: " + today);
    }

    @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Seoul") // 매일 00:00에 돌아가기
    public void nextDay() {
        this.yesterday = this.today;
        this.today = 0;
    }
}
