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
    private String department="";
    private String affiliation="none";
    private Boolean isRegistered=false;
    private String name="";
    private String email="";
    private String token="";
    private Boolean isHost=false;
    private String hostUntil="";
    private Boolean isPending=false;

}
