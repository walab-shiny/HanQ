package com.example.server.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccessCodeDto {
    private String code;
    private int eventId;
}
