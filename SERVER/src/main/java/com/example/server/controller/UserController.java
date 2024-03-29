package com.example.server.controller;

import com.example.server.dto.*;
import com.example.server.service.AttendService;
import com.example.server.service.UserService;
import com.example.server.token.DecodedToken;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final LoginCountController loginCountController;
    private final AttendService attendService;
    @PostMapping
    public ResponseEntity<LoginUserDto> login(@RequestBody TokenDto dto) {
        loginCountController.incrementLoginCount();
        return ResponseEntity.ok(userService.login(DecodedToken.getDecodedToken(dto.getCredential())));
    }
    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody RegisterUserDto dto) {
        return ResponseEntity.ok(userService.registerUser(dto));
    }
//    @PostMapping("/other")
//    public ResponseEntity<UserDto> registerOther(@RequestBody RegisterOtherDto dto) {
//        return userService.registerOther(dto);
//    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable int id) {
        return ResponseEntity.ok(userService.getUser(id).toDto());
    }
    @GetMapping
    public ResponseEntity<UserDto> getUserByToken(@RequestHeader(name = "Authorization") String token) {
        return ResponseEntity.ok(userService.getUserByToken(token).toDto());
    }
    @GetMapping("/attend/{id}")
    public ResponseEntity<List<AttendUserDto>> getAttendUsers(@PathVariable(name = "id") int id, Pageable pageable) {
        return ResponseEntity.ok(attendService.findAttendUsersByPage(id,pageable));
    }
    @PostMapping("/update")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserUpdateDto dto, @RequestHeader(name = "Authorization") String token) {
        return ResponseEntity.ok(userService.updateUser(dto, token));
    }
}
