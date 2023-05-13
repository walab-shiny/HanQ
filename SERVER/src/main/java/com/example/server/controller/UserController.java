package com.example.server.controller;

import com.example.server.dto.*;
import com.example.server.entity.Attend;
import com.example.server.entity.User;
import com.example.server.service.UserService;
import com.example.server.token.DecodedToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final LoginCountController loginCountController;
    @PostMapping
    public ResponseEntity<LoginUserDto> login(@RequestBody TokenDto dto) {
        loginCountController.incrementLoginCount();
        return userService.login(DecodedToken.getDecodedToken(dto.getCredential()));
    }
    @PostMapping("/student")
    public ResponseEntity<UserDto> registerStudent(@RequestBody RegisterStudentDto dto) {
        return userService.registerStudent(dto);
    }
    @PostMapping("/other")
    public ResponseEntity<UserDto> registerOther(@RequestBody RegisterOtherDto dto) {
        return userService.registerOther(dto);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable int id) {
        return ResponseEntity.ok(userService.getUser(id).toDto());
    }
    @GetMapping
    public ResponseEntity<UserDto> getUserByToken(@RequestHeader(name = "Authorization") String token) {
        return ResponseEntity.ok(userService.getUserByToken(token).toDto());
    }
    @GetMapping("/attend/{id}")
    public ResponseEntity<List<AttendUserDto>> getAttendUsers(@PathVariable(name = "id") int id) {
        return ResponseEntity.ok(userService.getAttendUsers(id));
    }
    @PostMapping("/update")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserUpdateDto dto, @RequestHeader(name = "Authorization") String token) {
        return ResponseEntity.ok(userService.updateUser(dto, token));
    }
}
