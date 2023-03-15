package com.example.server.entity;

import com.example.server.entity.base.BaseEntity;
import com.example.server.entity.relation.EventCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "events")
public class Event extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private LocalDateTime openAt;
    @ManyToOne(fetch = FetchType.LAZY)
    private User host;
    private String location;
    private int maxUsers;
    @OneToMany(mappedBy = "event")
    private List<EventCategory> categories = new ArrayList<>();
    private String content;
    private LocalDateTime lateTime;
    private String image;
}
