package com.example.server.dto;

import com.example.server.entity.HostAuthRequest;
import com.example.server.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HostAuthRequestDto {
    private int id;
    private RequestUserDto user;
    private String affiliation;
    private LocalDate hostUntil;
    private int status;
    private String content;
    private String response="";
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public HostAuthRequestDto(HostAuthRequest request) {
        this.id = request.getId();
        this.user = request.getUser().toRequestUserDto();
        this.status = request.getStatus();
        this.content = request.getContent();
        this.hostUntil = request.getHostUntil();
        this.affiliation = request.getAffiliation();
        if(request.getResponse()!=null)
            this.response = request.getResponse();
        this.createdAt = request.getCreatedAt();
        this.modifiedAt = request.getModifiedAt();
    }
}
