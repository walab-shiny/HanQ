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
public class ReportDto {
    private int id;
    private String eventName;
    private Long studentNum;
    private String name;
    private String content;
    private LocalDateTime modifiedAt;
}
