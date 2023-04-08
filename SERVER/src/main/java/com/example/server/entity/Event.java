package com.example.server.entity;

import com.example.server.dto.EventDto;
import com.example.server.dto.EventUpdateDto;
import com.example.server.dto.TagDto;
import com.example.server.entity.base.BaseEntity;
import com.example.server.entity.relation.EventTag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "events")
public class Event extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private LocalDateTime openAt;
    private LocalDateTime closeAt;
    private Boolean closed=false;
    private int reportTimeLimit;
    @ManyToOne(fetch = FetchType.LAZY)
    private User host;
    private String location;
    private int maxUsers;
    @OneToMany(mappedBy = "event")
    private List<EventTag> tags = new ArrayList<>();
    private String content;
    private int availableTime;
    private String image;

    public Event (String name, LocalDateTime openAt, User host, String location, int maxUsers, String content, int availableTime, String image){
        this.name=name;
        this.openAt=openAt;
        this.host=host;
        this.location = location;
        this.maxUsers = maxUsers;
        this.content = content;
        this.availableTime = availableTime;
        this.image = image;
    }

    public void setTags(List<EventTag> tags) {
        this.tags = tags;
    }
    public EventDto toDto(List<Tag> tags) {
        List<TagDto> tagDtos = tags.stream().map(Tag::toDto).collect(Collectors.toList());
        return new EventDto(this.id, this.name, this.openAt,this.closeAt,this.closed,this.reportTimeLimit, this.host.toDto(), this.location, this.maxUsers, tagDtos, this.content, this.availableTime, this.image);
    }
    public void setHost(User user) {
        user.getEvents().add(this);
        this.host = user;
    }
    public void update(EventUpdateDto dto) {
        this.name = dto.getName();
        this.openAt = LocalDateTime.parse(dto.getOpenAt());
        this.location = dto.getLocation();
        this.maxUsers = dto.getMaxUsers();
        this.content = dto.getContent();
        this.availableTime = dto.getAvailableTime();
        this.image = dto.getImage();
    }
}
