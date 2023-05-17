package com.example.server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegisterOtherDto {
    private int userId;
    private String affiliation;
    private Long studentNum;
}
