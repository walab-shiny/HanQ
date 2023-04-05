package com.example.server.dto;

import com.example.server.entity.Tag;
import com.example.server.entity.User;
import com.example.server.entity.relation.EventTag;
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
    private UserDto host;
    private String location;
    private int maxUsers;
    private List<TagDto> tags;
    private String content;
    private int availableTime;
    private String image;
}
