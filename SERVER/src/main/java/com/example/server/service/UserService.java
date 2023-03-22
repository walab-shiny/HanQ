package com.example.server.service;

import com.example.server.dto.LoginUserDto;
import com.example.server.dto.RegisterOtherDto;
import com.example.server.dto.RegisterStudentDto;
import com.example.server.dto.UserDto;
import com.example.server.entity.Department;
import com.example.server.entity.User;
import com.example.server.repository.CategoryRepository;
import com.example.server.repository.DepartmentRepository;
import com.example.server.repository.UserRepository;
import com.example.server.token.DecodedToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final DepartmentRepository departmentRepository;
    @Transactional
    public ResponseEntity<LoginUserDto> login(DecodedToken token) {
        LoginUserDto dto = new LoginUserDto();
        dto.setRegistered(userRepository.existsUserByToken(token.getSub()));
        User saved;
        if(!dto.isRegistered()) {
            User user = new User(token);
            saved = userRepository.save(user);
        }
        else {
            saved = userRepository.findUserByToken(token.getSub());
        }
        dto.setUserId(saved.getId());
        return ResponseEntity.ok(dto);
    }
    @Transactional
    public ResponseEntity<UserDto> getUser(int id) {
        User saved = userRepository.findById(id).orElseThrow();
        return ResponseEntity.ok(saved.toDto());
    }
    @Transactional
    public ResponseEntity<UserDto> registerStudent(@RequestBody RegisterStudentDto dto) {
        User user = userRepository.findById(dto.getUserId()).orElseThrow();
        Department department = departmentRepository.findById(dto.getDepartmentId()).orElseThrow();
        user.setDepartment(department);
        user.setStudentNum(dto.getStudentNum());
        return ResponseEntity.ok(user.toDto());
    }
    @Transactional
    public ResponseEntity<UserDto> registerOther(@RequestBody RegisterOtherDto dto) {
        User user = userRepository.findById(dto.getUserId()).orElseThrow();
        user.setAffiliation(dto.getAffiliation());
        return ResponseEntity.ok(user.toDto());
    }
}
