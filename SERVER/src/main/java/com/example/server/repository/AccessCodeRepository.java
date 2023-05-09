package com.example.server.repository;

import com.example.server.entity.AccessCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessCodeRepository extends JpaRepository<AccessCode,Integer> {
    public boolean existsAccessCodeByCode(String code);
}
