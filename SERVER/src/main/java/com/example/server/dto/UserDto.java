package com.example.server.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {
    private int id;
    private Long studentNum=0L;
    private Boolean isStudent=false;
    private String department="";
    private String affiliation="none";
    private Boolean isRegistered=false;
    private String name="";
    private String email="";
    private String token="";
    private Boolean isHost=false;
    private String hostUntil="";
    private Boolean isPending=false;
    private String picture="";
    private String requestDate="";
    private List<TagDto> tags = new ArrayList<>();
}
