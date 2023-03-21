package com.example.server.admin;

import com.example.server.dto.AdminCreateDto;
import com.example.server.dto.AdminDto;
import com.example.server.service.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AdminCreateTest {
    @Autowired
    AdminService adminService;

    @Test
    public void createAdmin() {
        AdminDto dto = adminService.addAdmin(new AdminCreateDto("admin","admin"));
        assertThat(dto.getUsername()).isEqualTo("admin");
        System.out.println("dto = " + dto);
    }
}
