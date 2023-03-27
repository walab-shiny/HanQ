package com.example.server.controller;

import com.example.server.dto.*;
import com.example.server.service.AdminService;
import com.example.server.service.HostAuthRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;
    private final HostAuthRequestService hostAuthRequestService;
    @PostMapping
    public ResponseEntity<AdminDto> createAdmin(@RequestBody AdminCreateLoginDto dto) {
        return ResponseEntity.ok(adminService.addAdmin(dto));
    }
    @PostMapping("/login")
    public ResponseEntity<AdminLoggedInDto> loginAdmin(@RequestBody AdminCreateLoginDto dto) {
        return ResponseEntity.ok(adminService.login(dto));
    }
    @PostMapping("/requests/accept")
    public ResponseEntity<HostAuthRequestDto> acceptRequest(@RequestBody AcceptHostRequestDto dto) {
        return ResponseEntity.ok(hostAuthRequestService.acceptRequest(dto));
    }
    @PostMapping("/requests/decline")
    public ResponseEntity<HostAuthRequestDto> declineRequest(@RequestBody DeclineHostRequestDto dto) {
        return ResponseEntity.ok(hostAuthRequestService.declineRequest(dto));
    }
    @GetMapping("/requests/status/{status}")
    public ResponseEntity<List<HostAuthRequestDto>> getListByStatus(@PathVariable int status) {
        return ResponseEntity.ok(hostAuthRequestService.getRequests(status));
    }
    @GetMapping("/requests")
    public ResponseEntity<List<HostAuthRequestDto>> getList() {
        return ResponseEntity.ok(hostAuthRequestService.getRequests());
    }
    @GetMapping("/requests/{id}")
    public ResponseEntity<HostAuthRequestDto> getRequest(@PathVariable int id) {
        return ResponseEntity.ok(hostAuthRequestService.getRequest(id));
    }
}
