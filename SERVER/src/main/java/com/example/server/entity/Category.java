package com.example.server.entity;

import com.example.server.entity.relation.EventCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name="categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(mappedBy = "category")
    private List<EventCategory> events = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User likes;
}
