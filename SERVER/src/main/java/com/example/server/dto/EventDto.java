package com.example.server.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EventDto {
    private int id;
    private String name;
    private LocalDateTime openAt;
    private LocalDateTime closeAt;
    private Boolean closed;
    private int reportTimeLimit;
    private int hostId;
    private String location;
    private int maxUsers;
    private List<TagDto> tags;
    private String content;
    private int availableTime;
    private String image;
}
