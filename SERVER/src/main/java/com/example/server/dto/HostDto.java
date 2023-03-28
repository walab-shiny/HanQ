package com.example.server.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class HostDto {
    private int id;
    private Boolean isHost;
    private LocalDate hostUntil = LocalDate.of(2099,1,1);
}
