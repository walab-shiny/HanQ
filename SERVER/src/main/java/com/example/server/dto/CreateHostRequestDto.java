package com.example.server.dto;

import com.example.server.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@AllArgsConstructor
public class CreateHostRequestDto extends BaseEntity {
    private int userId;
    private String content;
}
