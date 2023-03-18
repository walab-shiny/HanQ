package com.example.server.service;

import com.example.server.dto.LoginUserDto;
import com.example.server.dto.UserDto;
import com.example.server.entity.User;
import com.example.server.repository.UserRepository;
import com.example.server.token.DecodedToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

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
    public ResponseEntity<UserDto> getUser(int id) {
        User saved = userRepository.findById(id).orElseThrow();
        return ResponseEntity.ok(saved.toDto());
    }
}
