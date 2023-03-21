package com.example.server.entity;

import com.example.server.dto.AdminCreateLoginDto;
import com.example.server.dto.AdminDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "admins")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;

    public Admin(AdminCreateLoginDto dto) {
        this.username = dto.getUsername();
        this.password = DigestUtils.sha256Hex(dto.getPassword());
    }
    public AdminDto toDto() {
        return new AdminDto(this.id,this.username,this.password);
    }
}
