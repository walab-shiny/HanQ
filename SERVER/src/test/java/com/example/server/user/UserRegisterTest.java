package com.example.server.user;

import com.example.server.dto.LoginUserDto;
import com.example.server.dto.RegisterOtherDto;
import com.example.server.dto.RegisterStudentDto;
import com.example.server.dto.UserDto;
import com.example.server.repository.DepartmentRepository;
import com.example.server.service.DepartmentService;
import com.example.server.service.UserService;
import com.example.server.token.DecodedToken;
import com.mysql.cj.log.Log;
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

//    @Test
//    @Order(2)
//    public void registerOther() {
//        ResponseEntity<UserDto> dto = userService.registerOther(new RegisterOtherDto(1,"전산전자공학부 임원단"));
//        System.out.println("dto.toString() = " + dto.toString());
//        assertThat(Objects.requireNonNull(dto.getBody()).getIsRegistered()).isEqualTo(true);
//        assertThat(dto.getBody().getAffiliation()).isEqualTo("전산전자공학부 임원단");
//        assertThat(dto.getBody().getIsStudent()).isEqualTo(false);
//    }

    @Test
    @Order(2)
    public void registerStudent() {
        ResponseEntity<LoginUserDto> login = userService.login(DecodedToken.getDecodedToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6Iu2Vmeu2gOyDneuwsOyjvOyYgSIsImVtYWlsIjoiYWlkYW5iYWVAaGFuZG9uZy5hYy5rciIsImZhbWlseV9uYW1lIjoi67Cw7KO87JiBIiwiaWF0IjoxNTE2MjM5MDIyfQ.S5nKMZTVZOV0cZpb4tEpEYxDEsArj3DlBbXxdOCpmGA"));
        assertThat(Objects.requireNonNull(login.getBody()).getUserId()).isEqualTo(1);
        assertThat(login.getBody().isStudent()).isEqualTo(true);
        assertThat(login.getBody().isRegistered()).isEqualTo(false);
        ResponseEntity<UserDto> dto = userService.registerStudent(new RegisterStudentDto(1, 22000328L, 1));
        assertThat(Objects.requireNonNull(dto.getBody()).getDepartmentId()).isEqualTo(1);
        assertThat(dto.getBody().getStudentNum()).isEqualTo(22000328L);
        assertThat(dto.getBody().getIsRegistered()).isEqualTo(true);
    }
    @Test
    @Order(3)
    public void registerOther() {
        ResponseEntity<LoginUserDto> login = userService.login(DecodedToken.getDecodedToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI4MzI0Nzg0NzM0NyIsIm5hbWUiOiJBaWRhbiBCYWUiLCJlbWFpbCI6InRoaWNraXNod2hhcmY5QGdtYWlsLmNvbSJ9.SNI8TKVzPyjEsH3UMVEvDV7dEKUk4RihRdno-gbeKVc"));
        assertThat(Objects.requireNonNull(login.getBody()).getUserId()).isEqualTo(2);
        assertThat(login.getBody().isStudent()).isEqualTo(false);
        assertThat(login.getBody().isRegistered()).isEqualTo(false);
        ResponseEntity<UserDto> dto = userService.registerOther(new RegisterOtherDto(2,"전산전자공학부 임원단"));
        assertThat(Objects.requireNonNull(dto.getBody()).getAffiliation()).isEqualTo("전산전자공학부 임원단");
        assertThat(dto.getBody().getIsRegistered()).isEqualTo(true);
    }

}
