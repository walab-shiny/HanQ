package com.example.server.controller;

import com.example.server.dto.CreateHostRequestDto;
import com.example.server.dto.HostAuthRequestDto;
import com.example.server.service.HostAuthRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/host")
public class HostAuthController {
    private final HostAuthRequestService hostAuthRequestService;
    @PostMapping
    public HostAuthRequestDto createRequest(@RequestBody CreateHostRequestDto dto) {
        return hostAuthRequestService.createAuthRequest(dto).toDto();
    }
}
