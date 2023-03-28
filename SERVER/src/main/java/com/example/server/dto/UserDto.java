package com.example.server.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {
    private int id;
    private Long studentNum=0L;
    private Boolean isStudent=false;
    private int departmentId=0;
    private String affiliation="none";
    private Boolean isRegistered=false;
    private String name="";
    private String email="";
    private String token="";
    private Boolean isHost=false;

}
