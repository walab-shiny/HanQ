package com.example.server.entity;

import com.example.server.dto.UserDto;
import com.example.server.entity.base.BaseEntity;
import com.example.server.token.DecodedToken;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "users")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private Long studentId;
    @Column
    private boolean isStudent = true;
    @OneToOne
    private Role role;
    @OneToOne
    private Department department;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String token;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private List<Attend> attends = new ArrayList<>();
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "host")
    private List<Event> events = new ArrayList<>();
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="likes")
    private List<Category> categories = new ArrayList<>();

    public User(DecodedToken token) {
        this.email = token.getEmail();
        this.token = token.getSub();
        this.name = token.getName();
    }
    public UserDto toDto() {
        UserDto dto = new UserDto();
        dto.setId(this.id);
        dto.setEmail(this.email);
        dto.setName(this.name);
        dto.setToken(this.token);
        // later fix
//        dto.setDepartmentId(this.department.getId());
//        dto.setRoleId(this.role.getId());
//        dto.setStudentId(this.studentId);
        return dto;
    }
}
