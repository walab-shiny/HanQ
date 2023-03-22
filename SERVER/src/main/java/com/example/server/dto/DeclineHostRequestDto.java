package com.example.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeclineHostRequestDto {
    private int id;
    private String response;
}
