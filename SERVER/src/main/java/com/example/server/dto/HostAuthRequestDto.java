package com.example.server.dto;

import com.example.server.entity.HostAuthRequest;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HostAuthRequestDto {
    private int id;
    private int userId;
    private int status;
    private String content;

    public HostAuthRequestDto(HostAuthRequest request) {
        this.id = request.getId();
        this.userId = request.getUser().getId();
        this.status = request.getStatus();
        this.content = request.getContent();
    }
}
