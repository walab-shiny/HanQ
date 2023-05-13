package com.example.server.repository;

import com.example.server.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report,Integer> {
    List<Report> getReportsByEvent_Id(int id);
    @Query("select r from Report r where r.user.token = ?1")
    List<Report> getReportsByUser_Token(String token);
}
