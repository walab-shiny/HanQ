package com.example.server.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUserDto {
    private int userId;
    private Boolean isRegistered;
    private Boolean isStudent;
}
