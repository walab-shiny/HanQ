package com.example.server.entity;

import com.example.server.dto.RequestUserDto;
import com.example.server.dto.UserDto;
import com.example.server.entity.base.BaseEntity;
import com.example.server.entity.relation.UserTag;
import com.example.server.qr.Result;
import com.example.server.token.DecodedToken;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    @Column
    private Boolean isHost=false;
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HostAuthRequest> requests = new ArrayList<>();
    @Column
    private String affiliation;
    @OneToOne
    private Department department;
    @Column
    private LocalDate hostUntil;
    @Column
    private Boolean isPending;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String picture;
    @Column
    private String token;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private List<Attend> attends = new ArrayList<>();
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "host")
    private List<Event> events = new ArrayList<>();
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="user")
    private List<UserTag> tags = new ArrayList<>();
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private List<Report> reports = new ArrayList<>();
    public User(Result result) {
        this.name = result.getUser_name();
        if(result.getUser_number().length()!=8)
            this.isStudent = false;
        this.studentNum = Long.valueOf(result.getUser_number());
    }
    public User(DecodedToken token) {
        if(token.getName().contains("학부생"))
            this.name = token.getFamily_name();
        else {
            this.name = token.getName();
            this.isStudent=false;
        }
        this.email = token.getEmail();
        this.token = token.getSub();
        this.picture = token.getPicture();
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
    public void setAffiliationFromHostRequest(String affiliation) {
        this.affiliation = affiliation;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }

    public void addRequest(HostAuthRequest request) {
        this.requests.add(request);
        this.isPending=true;
    }
    public Boolean getIsHost() {
        return this.isHost;
    }
    public void makeHost() {
        this.isHost = true;
    }
    public void setIsPending(Boolean isPending) {
        this.isPending = isPending;
    }
    public void setHostUntil(String date) {
        this.hostUntil = LocalDate.parse(date);
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
                dto.setDepartment(this.department.getName());
                dto.setPicture(this.picture);
            }
            dto.setAffiliation(this.affiliation);
        }
        dto.setToken(this.token);
        dto.setIsRegistered(this.isRegistered);
        dto.setIsHost(this.isHost);
        if(this.hostUntil!=null)
            dto.setHostUntil(this.hostUntil.toString());
        if(this.isPending!=null)
            dto.setIsPending(this.isPending);
        if(this.requests.size()!=0)
            dto.setRequestDate(String.valueOf(this.requests.get(this.requests.size()-1).getCreatedAt()));
        if(!this.tags.isEmpty()) {
            dto.setTags(tags.stream().map(userTag -> userTag.getTag().toDto()).collect(Collectors.toList()));
        }
        return dto;
    }
    public RequestUserDto toRequestUserDto() {
        if(this.isStudent)
            return new RequestUserDto(this.name,this.picture,this.affiliation,this.email,this.getDepartment().getName(),this.getStudentNum(),this.hostUntil);
        else
            return new RequestUserDto(this.name,this.picture,this.affiliation,this.email,"없음",0L,this.hostUntil);
    }
    public void addAttend(Attend attend) {
        this.attends.add(attend);
    }

    public void swap(User user) {
        this.token = user.getToken();
        this.email = user.getEmail();
        this.name = user.getName();
        this.picture = user.getPicture();
    }

    public void quitHost() {
        this.isHost=false;
    }
    public boolean isHostFor(int id) {
        return (this.events.stream().anyMatch(e -> e.getId() == id));
    }
    public void setPicture(String picture) {
        this.picture = picture;
    }
    public void setTags(List<UserTag> tags) {
        this.tags = tags;
        tags.forEach(t -> t.setUser(this));
    }
    public void addReport(Report report) {
        this.reports.add(report);
    }


}
