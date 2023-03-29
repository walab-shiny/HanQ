package com.example.server.dto;

import com.example.server.entity.HostAuthRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@ToString
public class HostAuthRequestDto {
    private int id;
    private int userId;
    private int status;
    private String content;
    private String response="";
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public HostAuthRequestDto(HostAuthRequest request) {
        this.id = request.getId();
        this.userId = request.getUser().getId();
        this.status = request.getStatus();
        this.content = request.getContent();
        if(request.getResponse()!=null)
            this.response = request.getResponse();
        this.createdAt = request.getCreatedAt();
        this.modifiedAt = request.getModifiedAt();
    }
}
