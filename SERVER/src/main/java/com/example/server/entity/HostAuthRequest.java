package com.example.server.entity;

import com.example.server.dto.CreateHostRequestDto;
import com.example.server.dto.HostAuthRequestDto;
import com.example.server.entity.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    private String response;

    public void setUser(User user) {
        this.user = user;
        user.setRequest(this);
    }
    public HostAuthRequest(CreateHostRequestDto dto) {
        this.content = dto.getContent();
    }
    public HostAuthRequestDto toDto() {
        return new HostAuthRequestDto(this);
    }
    public void writeResponse(String response) {
        this.response = response;
    }
    public void accept() {
        this.status = 2;
    }
    public void decline() {
        this.status=0;
    }
}
