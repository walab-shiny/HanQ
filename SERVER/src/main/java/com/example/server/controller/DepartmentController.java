package com.example.server.controller;

import com.example.server.dto.DepartmentDto;
import com.example.server.dto.DepartmentInsertDto;
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
    public ResponseEntity<DepartmentDto> addDepartment(@RequestBody DepartmentInsertDto dto) {
        return ResponseEntity.ok(departmentService.addDepartment(dto.getName()).toDto());
    }
}
