package com.example.server.entity.relation;

import com.example.server.entity.Tag;
import com.example.server.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User likes;
    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;
}
