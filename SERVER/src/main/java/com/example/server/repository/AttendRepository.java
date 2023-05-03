package com.example.server.repository;

import com.example.server.entity.Attend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AttendRepository extends JpaRepository<Attend, Integer> {
    boolean existsAttendByUserStudentNumAndEventId(Long number,int eventId);
    List<Attend> getAttendsByUserId(int id);
    Attend getAttendByUser_StudentNumAndEventId(Long number, int eventId);
    List<Attend> getAttendByTaggedAtBetween(LocalDateTime a, LocalDateTime b);
}
