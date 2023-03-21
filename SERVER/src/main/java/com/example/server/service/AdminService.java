package com.example.server.service;

import com.example.server.dto.AdminCreateDto;
import com.example.server.dto.AdminDto;
import com.example.server.entity.Admin;
import com.example.server.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;

    @Transactional
    public AdminDto addAdmin(AdminCreateDto dto) {
        Admin admin = new Admin(dto);
        return adminRepository.save(admin).toDto();
    }
    public AdminDto login(AdminCreateDto dto) {
        Admin found = adminRepository.findAdminByUsername(dto.getUsername());

        return found.toDto();
    }

}
