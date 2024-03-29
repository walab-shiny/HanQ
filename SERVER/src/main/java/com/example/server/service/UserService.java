package com.example.server.service;

import com.example.server.dto.*;
import com.example.server.entity.*;
import com.example.server.entity.relation.UserTag;
import com.example.server.repository.DepartmentRepository;
import com.example.server.repository.EventRepository;
import com.example.server.repository.UserRepository;
import com.example.server.repository.UserTagRepository;
import com.example.server.token.DecodedToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final EventRepository eventRepository;
    private final TagService tagService;
    private final UserTagService userTagService;
    @Transactional
    public LoginUserDto login(DecodedToken token) {
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
        dto.setIsRegistered(saved.getIsRegistered());
        dto.setIsStudent(saved.getIsStudent());
        return dto;
    }
    @Transactional
    public User getUser(int id) {
        return userRepository.findById(id).orElseThrow();
    }
    @Transactional
    public UserDto registerUser(RegisterUserDto dto) {
        User user;
        if(dto.getStudentNum()!=0 && userRepository.existsUserByStudentNum(dto.getStudentNum())) {
            user = userRepository.findUserByStudentNum(dto.getStudentNum());
            User temp = userRepository.findById(dto.getUserId()).orElseThrow();
            user.swap(temp);
            userRepository.delete(temp);
        }
        else {
            user = userRepository.findById(dto.getUserId()).orElseThrow();
        }
        if(dto.getDepartmentId()!=0) {
            Department department = departmentRepository.findById(dto.getDepartmentId()).orElseThrow();
            user.setDepartment(department);
        }
        if(!dto.getAffiliation().isEmpty()) {
            user.setAffiliation(dto.getAffiliation());
        }
        user.setStudentNum(dto.getStudentNum());
        return user.toDto();
    }

    @Transactional
    public User getUserByToken(String token) {
        return userRepository.findUserByToken(token);
    }

    public List<AttendUserDto> getAttendUsers(int id) {
        Event event = eventRepository.findById(id).orElseThrow();
        List<Attend> attends = event.getAttends();
        List<AttendUserDto> list = attends.stream().map(Attend::toAttendUserDto).collect(Collectors.toList());
        return list.stream().distinct().sorted(Comparator.comparing(AttendUserDto::getTaggedAt)).collect(Collectors.toList());
    }

    @Transactional
    @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Seoul")
    public void hostUntilScheduled() {
        List<User> hosts = userRepository.findUsersByIsHostIsTrue();
        hosts.forEach(h -> {
            if(h.getHostUntil()!= null && h.getHostUntil().isBefore(LocalDate.now()))
                h.quitHost();
        });
    }
    @Transactional
    public UserDto updateUser(UserUpdateDto dto, String token) {
        User user = getUserByToken(token);
        userTagService.deleteRelations(user.getId());
        List<Tag> tags = tagService.getTagsFromList(dto.getLikes());
        List<UserTag> userTags = userTagService.createRelations(tags,user);
        if(!dto.getPicture().isEmpty())
            user.setPicture(dto.getPicture());
        if(!userTags.isEmpty()) {
            user.setTags(userTags);
        }
        UserDto returnDto = user.toDto();
        return returnDto;
    }
}
