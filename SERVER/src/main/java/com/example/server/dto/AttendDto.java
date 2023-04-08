package com.example.server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttendDto {
    private int id;
    private int status;
    private String memo;
    private int userId;
    private LocalDateTime taggedAt;
    private int eventId;
}
