package com.example.server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttendUserDto {
    private Long studentNum;
    private String name;
    private String department="없음";
    private String memo="";
    private LocalDateTime taggedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttendUserDto that = (AttendUserDto) o;
        return Objects.equals(studentNum, that.studentNum) && Objects.equals(taggedAt, that.taggedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentNum, taggedAt);
    }
}
