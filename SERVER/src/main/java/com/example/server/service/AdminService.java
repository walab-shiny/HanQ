package com.example.server.service;

import com.example.server.dto.AdminCreateLoginDto;
import com.example.server.dto.AdminDto;
import com.example.server.dto.AdminLoggedInDto;
import com.example.server.entity.Admin;
import com.example.server.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;

    @Transactional
    public AdminDto addAdmin(AdminCreateLoginDto dto) {
        Admin admin = new Admin(dto);
        return adminRepository.save(admin).toDto();
    }
    public AdminLoggedInDto login(AdminCreateLoginDto dto) {
        Admin found = adminRepository.findAdminByUsername(dto.getUsername());
        if(found==null) {
            return new AdminLoggedInDto(false);
        }
        String encrypted = DigestUtils.sha256Hex(dto.getPassword());
            return new AdminLoggedInDto(found.getPassword().equals(encrypted));
    }

}
