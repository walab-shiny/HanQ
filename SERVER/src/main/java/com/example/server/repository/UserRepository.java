package com.example.server.repository;

import com.example.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    @Query("select (count(u) > 0) from User u where u.token = ?1")
    boolean existsUserByToken(String token);
    @Query("select u from User u where u.token = ?1")
    User findUserByToken(String token);
    @Query("select u from User u where u.studentNum = ?1")
    User findUserByStudentNum(Long number);
    @Query("select (count(u) > 0) from User u where u.studentNum = ?1")
    boolean existsUserByStudentNum(Long number);
    @Query("select u from User u where u.isHost = true")
    List<User> findUsersByIsHostIsTrue();
}
