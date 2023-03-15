package com.example.server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "hostAuthRequests")
public class HostAuthRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int status=1;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
