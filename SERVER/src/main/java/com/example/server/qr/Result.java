package com.example.server.qr;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private String user_number;
    private String user_name;
    private String dept_name;
    private String major1_name;
    private String major2_name;
    private String qr_tagging_time;
}
