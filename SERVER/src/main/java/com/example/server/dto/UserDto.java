package com.example.server.dto;

import com.example.server.entity.Department;
import com.example.server.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private int id;
    private Long studentId=0L;
    private int departmentId=0;
    private int roleId=0;
    private String name;
    private String email;
    private String token;
}
