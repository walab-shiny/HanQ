package com.example.server.repository;

import com.example.server.entity.HostAuthRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HostAuthRequestRepository extends JpaRepository<HostAuthRequest,Integer> {
    List<HostAuthRequest> findAllByStatus(int status);
}
