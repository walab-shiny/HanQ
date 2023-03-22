package com.example.server.entity;

import com.example.server.dto.DepartmentDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public Department(String name) {
        this.name=name;
    }

    public DepartmentDto toDto() {
        DepartmentDto dto = new DepartmentDto();
        dto.setId(this.id);
        dto.setName(this.name);
        return dto;
    }
}
