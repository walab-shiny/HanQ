package com.example.server.entity;

import com.example.server.dto.TagCreateDto;
import com.example.server.dto.TagDto;
import com.example.server.entity.relation.EventTag;
import com.example.server.entity.relation.UserTag;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Table(name="tags")
@NoArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(mappedBy = "tag")
    private List<EventTag> events;
    @OneToMany(mappedBy="tag")
    private List<UserTag> users;

    public TagDto toDto() {
        return new TagDto(this.id, this.name);
    }
    public Tag(TagCreateDto dto) {
        this.name = dto.getName();
    }

}
