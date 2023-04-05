package com.example.server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventCreateDto {
    private String name;
    private String openAt;
    private String location;
    private int maxUsers;
    private String content;
    private int availableTime;
    private String image;
    private List<Integer> tags;
}
