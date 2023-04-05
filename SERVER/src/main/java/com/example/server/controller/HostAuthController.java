package com.example.server.controller;

import com.example.server.dto.CreateHostRequestDto;
import com.example.server.dto.HostAuthRequestDto;
import com.example.server.service.HostAuthRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/host")
public class HostAuthController {
    private final HostAuthRequestService hostAuthRequestService;
    @PostMapping
    public ResponseEntity<HostAuthRequestDto> createRequest(@RequestBody CreateHostRequestDto dto, @RequestHeader(name = "Authorization") String token) {
        return ResponseEntity.ok(hostAuthRequestService.createAuthRequest(dto,token).toDto());
    }
}
