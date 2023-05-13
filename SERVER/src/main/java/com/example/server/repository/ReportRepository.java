package com.example.server.repository;

import com.example.server.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report,Integer> {
    @Query("select r from Report r where r.event.id = ?1")
    List<Report> getReportsByEvent_Id(int id);
    @Query("select r from Report r where r.user.token = ?1")
    List<Report> getReportsByUser_Token(String token);
    @Query("select r from Report r where r.user.id = ?1 and r.event.id = ?2")
    Optional<Report> getReportByUser_IdAndEvent_Id(int userId, int eventId);
}
