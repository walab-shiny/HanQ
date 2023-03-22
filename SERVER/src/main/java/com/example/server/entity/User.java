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
    private Long studentNum;
    @Column
    private Boolean isStudent = true;
    @Column
    private Boolean isRegistered=false;
    @OneToOne
    private Role role;
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HostAuthRequest> requests = new ArrayList<>();
    @Column
    private String affiliation;
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
        if(token.getName().contains("학부생"))
            this.name = token.getFamily_name();
        else {
            this.name = token.getName();
            this.isStudent=false;
        }
        this.email = token.getEmail();
        this.token = token.getSub();
    }
    public void setStudentNum(Long studentNum) {
        this.studentNum = studentNum;
        this.isRegistered = true;
    }
    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
        this.isStudent = false;
        this.isRegistered = true;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setRequest(HostAuthRequest request) {
        this.requests.add(request);
    }
    public UserDto toDto() {
        UserDto dto = new UserDto();
        dto.setId(this.id);
        dto.setEmail(this.email);
        dto.setName(this.name);
        dto.setIsStudent(this.isStudent);
        if(this.isRegistered) {
            if(dto.getIsStudent()) {
                dto.setStudentNum(this.studentNum);
                dto.setDepartmentId(this.department.getId());
            }
            else {
                dto.setAffiliation(this.affiliation);
            }
        }
        dto.setToken(this.token);
        dto.setIsRegistered(this.isRegistered);
        // later fix
//        dto.setRoleId(this.role.getId());
        return dto;
    }

}
