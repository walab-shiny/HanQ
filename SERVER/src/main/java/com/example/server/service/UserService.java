package com.example.server.service;

import com.example.server.dto.LoginUserDto;
import com.example.server.dto.RegisterOtherDto;
import com.example.server.dto.RegisterStudentDto;
import com.example.server.dto.UserDto;
import com.example.server.entity.Department;
import com.example.server.entity.User;
import com.example.server.repository.DepartmentRepository;
import com.example.server.repository.UserRepository;
import com.example.server.token.DecodedToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    @Transactional
    public ResponseEntity<LoginUserDto> login(DecodedToken token) {
        LoginUserDto dto = new LoginUserDto();
        User saved;
        if(!userRepository.existsUserByToken(token.getSub())) {
            User user = new User(token);
            saved = userRepository.save(user);
        }
        else {
            saved = userRepository.findUserByToken(token.getSub());
        }
        dto.setUserId(saved.getId());
        dto.setRegistered(saved.getIsRegistered());
        dto.setStudent(saved.getIsStudent());
        return ResponseEntity.ok(dto);
    }
    @Transactional
    public User getUser(int id) {
        return userRepository.findById(id).orElseThrow();
    }
    @Transactional
    public ResponseEntity<UserDto> registerStudent(RegisterStudentDto dto) {
        User user = userRepository.findById(dto.getUserId()).orElseThrow();
        Department department = departmentRepository.findById(dto.getDepartmentId()).orElseThrow();
        user.setDepartment(department);
        user.setStudentNum(dto.getStudentNum());
        return ResponseEntity.ok(user.toDto());
    }
    @Transactional
    public ResponseEntity<UserDto> registerOther(RegisterOtherDto dto) {
        User user = userRepository.findById(dto.getUserId()).orElseThrow();
        user.setAffiliation(dto.getAffiliation());
        return ResponseEntity.ok(user.toDto());
    }
    @Transactional
    public User getUserByToken(String token) {
        return userRepository.findUserByToken(token);
    }
}
