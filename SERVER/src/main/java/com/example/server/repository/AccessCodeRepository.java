package com.example.server.repository;

import com.example.server.entity.AccessCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccessCodeRepository extends JpaRepository<AccessCode,Integer> {
    boolean existsAccessCodeByCode(String code);

    void deleteAccessCodeByEvent_Id(int id);
}
