package com.example.server.controller;

import com.example.server.dto.AdminCreateLoginDto;
import com.example.server.dto.AdminDto;
import com.example.server.dto.AdminLoggedInDto;
import com.example.server.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;
    @PostMapping
    public ResponseEntity<AdminDto> createAdmin(@RequestBody AdminCreateLoginDto dto) {
        return ResponseEntity.ok(adminService.addAdmin(dto));
    }
    @PostMapping("/login")
    public ResponseEntity<AdminLoggedInDto> loginAdmin(@RequestBody AdminCreateLoginDto dto) {
        return ResponseEntity.ok(adminService.login(dto));
    }
}
