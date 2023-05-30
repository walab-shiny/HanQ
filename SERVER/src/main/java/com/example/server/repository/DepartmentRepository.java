package com.example.server.repository;

import com.example.server.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department,Integer> {
    @Query("select d from Department d where d.name = ?1")
    Optional<Department> findDepartmentByName(String name);
}
