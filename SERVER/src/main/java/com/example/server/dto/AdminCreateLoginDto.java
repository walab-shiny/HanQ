package com.example.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdminCreateLoginDto {
    private String username;
    private String password;
}
