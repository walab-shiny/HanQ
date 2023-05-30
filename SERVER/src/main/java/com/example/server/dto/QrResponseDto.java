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
    private String deptName;
    private String major1;
    private String major2;
    private String taggedAt;
    private int total;
    private Boolean isDuplicate=false;

    public QrResponseDto(Result result) {
        this.studentNum = result.getUser_number();
        this.name = result.getUser_name();
        this.deptName = result.getDept_name();
        this.major1 = result.getMajor1_name();
        this.major2 = result.getMajor2_name();
    }
}
