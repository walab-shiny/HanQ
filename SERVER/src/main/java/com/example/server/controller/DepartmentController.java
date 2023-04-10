package com.example.server.controller;

import com.example.server.dto.DepartmentDto;
import com.example.server.dto.DepartmentCreateDto;
import com.example.server.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/department")
public class DepartmentController {
    private final DepartmentService departmentService;
    @PostMapping
    public ResponseEntity<DepartmentDto> addDepartment(@RequestBody DepartmentCreateDto dto) {
        return ResponseEntity.ok(departmentService.addDepartment(dto.getName()).toDto());
    }
}
