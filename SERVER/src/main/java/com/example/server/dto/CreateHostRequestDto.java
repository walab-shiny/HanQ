package com.example.server.dto;

import com.example.server.entity.base.BaseEntity;
import lombok.*;
import org.springframework.stereotype.Service;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateHostRequestDto extends BaseEntity {
//    private int userId;
    private String content;
}
