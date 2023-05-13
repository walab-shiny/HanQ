package com.example.server.entity;

import com.example.server.dto.ReportDto;
import com.example.server.entity.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "reports")
public class Report extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String content;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public ReportDto toDto() {
        return new ReportDto(this.getId(),event.getName(),user.getStudentNum(),user.getName(),this.content,this.getModifiedAt());
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setUser(User user) {
        this.user = user;
        user.addReport(this);
    }
    public void setEvent(Event event) {
        this.event = event;

    }
}
