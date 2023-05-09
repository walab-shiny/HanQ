package com.example.server.entity;

import com.example.server.dto.AccessCodeDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "code")
public class AccessCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String code;
    @OneToOne(mappedBy = "accessCode")
    private Event event;

    public AccessCode(String code) {
        this.code = code;
    }
    public void setEvent(Event event) {
        this.event = event;
    }
    public AccessCodeDto toDto() {
        return new AccessCodeDto(code,event.getId());
    }
}
