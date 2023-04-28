package com.example.server.service;

import com.example.server.dto.*;
import com.example.server.entity.Attend;
import com.example.server.entity.Department;
import com.example.server.entity.Event;
import com.example.server.entity.User;
import com.example.server.repository.DepartmentRepository;
import com.example.server.repository.EventRepository;
import com.example.server.repository.UserRepository;
import com.example.server.token.DecodedToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final EventRepository eventRepository;
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
        User user;
        if(userRepository.existsUserByStudentNum(dto.getStudentNum())) {
            user = userRepository.findUserByStudentNum(dto.getStudentNum());
            User temp = userRepository.findById(dto.getUserId()).orElseThrow();
            user.swap(temp);
            userRepository.delete(temp);
        }
        else {
            user = userRepository.findById(dto.getUserId()).orElseThrow();
        }
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

    public List<AttendUserDto> getAttendUsers(int id, String token) {
        User host = userRepository.findUserByToken(token);
        if(host != null && host.getIsHost()) {
            Event event = eventRepository.findById(id).orElseThrow();
            List<Attend> attends = event.getAttends();
            return attends.stream().map(Attend::toAttendUserDto).collect(Collectors.toList());
        }
        else
            return null;
    }
}
