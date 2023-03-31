package com.example.server.entity;

import com.example.server.dto.TagCreateDto;
import com.example.server.dto.TagDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name="tags")
@NoArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    private Event event;
    @ManyToOne(fetch = FetchType.LAZY)
    private User likes;

    public TagDto toDto() {
        return new TagDto(this.id, this.name);
    }
    public Tag(TagCreateDto dto) {
        this.name = dto.getName();
    }
}
