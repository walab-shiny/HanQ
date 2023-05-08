package com.example.server.entity;

import com.example.server.dto.EventCreateDto;
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
    private Boolean isPublic=true;
    private int reportTimeLimit=0;
    @ManyToOne(fetch = FetchType.LAZY)
    private User host;
    private String location;
    private int maxUsers;
    @OneToMany(mappedBy = "event")
    private List<EventTag> tags = new ArrayList<>();
    @OneToMany(mappedBy = "event")
    private List<Attend> attends = new ArrayList<>();
    private String content;
    private int availableTime;
    private String image;
    private String affiliation;
    private int views=0;

    public Event (String name, LocalDateTime openAt, LocalDateTime closeAt,User host, String location, int maxUsers, int reportTimeLimit, String content, int availableTime, String image){
        this.name=name;
        this.openAt=openAt;
        this.closeAt = closeAt;
        this.host=host;
        this.location = location;
        this.maxUsers = maxUsers;
        this.reportTimeLimit = reportTimeLimit;
        this.content = content;
        this.availableTime = availableTime;
        this.image = image;
    }
    public Event (EventCreateDto dto) {
        this.name = dto.getName();
        this.openAt = LocalDateTime.parse(dto.getOpenAt());
        this.closeAt = LocalDateTime.parse(dto.getCloseAt());
        this.location = dto.getLocation();
        this.maxUsers = dto.getMaxUsers();
        this.reportTimeLimit = dto.getReportTimeLimit();
        this.content = dto.getContent();
        this.availableTime = dto.getAvailableTime();
        this.image = dto.getImage();
        this.isPublic = dto.getIsPublic();
    }

    public void setTags(List<EventTag> tags) {
        this.tags = tags;
    }
    public EventDto toDto(List<Tag> tags) {
        List<TagDto> tagDtos = tags.stream().map(Tag::toDto).collect(Collectors.toList());
        return new EventDto(this.id, this.name, this.openAt,this.closeAt,this.closed,this.reportTimeLimit, this.host.getId(), this.location, this.maxUsers, tagDtos, this.content, this.availableTime, this.image,this.isPublic,this.affiliation,this.views);
    }
    public void setHost(User user) {
        user.getEvents().add(this);
        this.host = user;
    }
    public void update(EventUpdateDto dto) {
        this.name = dto.getName();
        this.openAt = LocalDateTime.parse(dto.getOpenAt());
        this.closeAt = LocalDateTime.parse(dto.getCloseAt());
        this.location = dto.getLocation();
        this.maxUsers = dto.getMaxUsers();
        this.reportTimeLimit = dto.getReportTimeLimit();
        this.content = dto.getContent();
        this.availableTime = dto.getAvailableTime();
        this.image = dto.getImage();
        this.isPublic = dto.getIsPublic();
    }
    public void addAttend(Attend attend) {
        this.attends.add(attend);
    }

    public void close() {
        this.closed = true;
        this.closeAt = LocalDateTime.now();
    }
    public void setAffiliation() {
        this.affiliation = this.host.getAffiliation();
    }
    public void incrementViews() {
        this.views++;
    }

}
