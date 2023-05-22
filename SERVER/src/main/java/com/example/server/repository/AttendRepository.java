package com.example.server.repository;

import com.example.server.entity.Attend;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AttendRepository extends JpaRepository<Attend, Integer> {
    boolean existsAttendByUserStudentNumAndEventId(Long number,int eventId);
    List<Attend> getAttendsByUserId(int id);
    Attend getAttendByUser_StudentNumAndEventId(Long number, int eventId);
    List<Attend> getAttendByTaggedAtBetween(LocalDateTime a, LocalDateTime b);

    @Query("select distinct a from Attend a where a.event.id = ?1")
    Page<Attend> getDistinctByEventId(int id, Pageable pageable);

}
