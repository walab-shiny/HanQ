package com.example.server.service;

import com.example.server.entity.Department;
import com.example.server.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public Department addDepartment(String name) {
        return departmentRepository.save(new Department(name));
    }
}
