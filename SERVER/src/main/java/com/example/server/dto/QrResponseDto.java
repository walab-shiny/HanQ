package com.example.server.dto;

import com.example.server.qr.Result;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QrResponseDto {
    private String studentNum;
    private String name;
    private String taggedAt;
    private Boolean isDuplicate=false;

    public QrResponseDto(Result result) {
        this.studentNum = result.getUser_number();
        this.name = result.getUser_name();
    }
}
