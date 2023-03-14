package com.example.server.controller;

import com.example.server.dto.LoginUserDto;
import com.example.server.dto.TokenDto;
import com.example.server.service.UserService;
import com.example.server.token.DecodedToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    @PostMapping
    public ResponseEntity<LoginUserDto> login(@RequestBody TokenDto dto) throws UnsupportedEncodingException {
        return userService.login(DecodedToken.getDecodedToken(dto.getCredential()));
    }
}
