package com.example.server.entity;

import com.example.server.dto.AttendDto;
import com.example.server.entity.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "attends")
public class Attend extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private int status=1;
    @Column
    private String memo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    private LocalDateTime taggedAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="event_id")
    private Event event;

    public AttendDto toDto() {
        return new AttendDto(this.id,this.status,this.memo,this.user.getId(),this.taggedAt,this.event.getId());
    }
}

