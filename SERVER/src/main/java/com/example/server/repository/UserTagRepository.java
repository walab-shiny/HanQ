package com.example.server.repository;

import com.example.server.entity.relation.UserTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserTagRepository extends JpaRepository<UserTag,Integer> {
    List<UserTag> getUserTagsByLikes_Id(int id);
}
