package com.example.server.admin;

import com.example.server.dto.AdminCreateLoginDto;
import com.example.server.dto.AdminDto;
import com.example.server.dto.AdminLoggedInDto;
import com.example.server.service.AdminService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AdminCreateLoginTest {
    @Autowired
    AdminService adminService;

    @Test
    @Order(1)
    public void createAdmin() {
        AdminDto dto = adminService.addAdmin(new AdminCreateLoginDto("admin","admin"));
        assertThat(dto.getUsername()).isEqualTo("admin");
        System.out.println("dto = " + dto);
    }

    @Test
    @Order(2)
    public void loginAdmin() {
        AdminLoggedInDto success = adminService.login(new AdminCreateLoginDto("admin","admin"));
        AdminLoggedInDto fail = adminService.login(new AdminCreateLoginDto("admn","admin"));
        AdminLoggedInDto failAlso = adminService.login(new AdminCreateLoginDto("admin","admn"));
        assertThat(success.getMatches()).isEqualTo(true);
        assertThat(fail.getMatches()).isEqualTo(false);
        assertThat(failAlso.getMatches()).isEqualTo(false);

    }
}
