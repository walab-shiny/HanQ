package com.example.server.user;

import com.example.server.dto.RegisterOtherDto;
import com.example.server.dto.RegisterStudentDto;
import com.example.server.dto.UserDto;
import com.example.server.repository.DepartmentRepository;
import com.example.server.service.DepartmentService;
import com.example.server.service.UserService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserRegisterTest {
    @Autowired
    UserService userService;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    DepartmentService departmentService;

    @Test
    @Order(1)
    public void addDepartments() {
        departmentService.addDepartment("전산전자공학부");
        departmentService.addDepartment("콘텐츠융합디자인학부");
        departmentService.addDepartment("생명과학부");
        departmentService.addDepartment("공간환경시스템공학부");
        departmentService.addDepartment("기계제어공학부");
        departmentService.addDepartment("법학부");
        departmentService.addDepartment("경영경제학부");
        departmentService.addDepartment("ICT창업학부");
        departmentService.addDepartment("커뮤니케이션학부");
        assertThat(departmentRepository.findById(1).orElseThrow().getName()).isEqualTo("전산전자공학부");
        assertThat(departmentRepository.findById(2).orElseThrow().getName()).isEqualTo("콘텐츠융합디자인학부");
        assertThat(departmentRepository.findById(3).orElseThrow().getName()).isEqualTo("생명과학부");
        assertThat(departmentRepository.findById(4).orElseThrow().getName()).isEqualTo("공간환경시스템공학부");
        assertThat(departmentRepository.findById(5).orElseThrow().getName()).isEqualTo("기계제어공학부");
        assertThat(departmentRepository.findById(6).orElseThrow().getName()).isEqualTo("법학부");
        assertThat(departmentRepository.findById(7).orElseThrow().getName()).isEqualTo("경영경제학부");
        assertThat(departmentRepository.findById(8).orElseThrow().getName()).isEqualTo("ICT창업학부");
        assertThat(departmentRepository.findById(9).orElseThrow().getName()).isEqualTo("커뮤니케이션학부");

    }

    @Test
    @Order(2)
    public void registerOther() {
        ResponseEntity<UserDto> dto = userService.registerOther(new RegisterOtherDto(1,"전산전자공학부 임원단"));
        System.out.println("dto.toString() = " + dto.toString());
        assertThat(Objects.requireNonNull(dto.getBody()).getIsRegistered()).isEqualTo(true);
        assertThat(dto.getBody().getAffiliation()).isEqualTo("전산전자공학부 임원단");
        assertThat(dto.getBody().getIsStudent()).isEqualTo(false);
    }

//    @Test
//    @Order(3)
//    public void registerStudent() {
//        ResponseEntity<UserDto> dto = userService.registerStudent(new RegisterStudentDto(1, 22000328L, 1));
//        assertThat(Objects.requireNonNull(dto.getBody()).getDepartmentId()).isEqualTo(1);
//        assertThat(dto.getBody().getStudentNum()).isEqualTo(22000328L);
//        assertThat(dto.getBody().getIsRegistered()).isEqualTo(true);
//    }

}
