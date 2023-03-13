package com.example.server.repository;

import com.example.server.entity.HostAuthRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HostAuthRequestRepository extends JpaRepository<HostAuthRequest,Integer> {
}
