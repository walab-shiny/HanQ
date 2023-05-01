package com.example.server.entity;

import com.example.server.dto.CreateHostRequestDto;
import com.example.server.dto.HostAuthRequestDto;
import com.example.server.entity.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "hostAuthRequests")
public class HostAuthRequest extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int status=1;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String content;
    private String affiliation;
    private LocalDate hostUntil;
    private String response;

    public void setUser(User user) {
        this.user = user;
        user.addRequest(this);
    }
    public HostAuthRequest(CreateHostRequestDto dto) {
        this.content = dto.getContent();
        this.affiliation = dto.getAffiliation();
        this.hostUntil = LocalDate.now().plusDays(dto.getHostUntil());
    }
    public HostAuthRequestDto toDto() {
        HostAuthRequestDto dto = new HostAuthRequestDto();
        dto.setId(this.id);
        dto.setUser(this.user.toRequestUserDto());
        dto.setAffiliation(this.getAffiliation());
        dto.setHostUntil(this.getHostUntil());
        dto.setStatus(this.status);
        dto.setContent(this.content);
        dto.setResponse(this.response);
        dto.setCreatedAt(this.getCreatedAt());
        dto.setModifiedAt(this.getModifiedAt());
        return dto;
    }
    public void writeResponse(String response) {
        this.response = response;
    }
    public void accept(User user) {
        this.status = 2;
        user.makeHost();
        user.setIsPending(false);
    }
    public void decline(User user) {
        this.status=0;
        user.setIsPending(false);
    }
}
