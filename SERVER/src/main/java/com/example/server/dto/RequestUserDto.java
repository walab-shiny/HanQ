package com.example.server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestUserDto {
    private String name;
    private String picture;
    private String affiliation;
    private String email;
    private String department;
    private Long studentNum;
    private LocalDate hostUntil;
}
