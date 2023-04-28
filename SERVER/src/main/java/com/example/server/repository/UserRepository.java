package com.example.server.repository;

import com.example.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    boolean existsUserByToken(String token);
    User findUserByToken(String token);
    User findUserByStudentNum(Long number);
    boolean existsUserByStudentNum(Long number);
}
