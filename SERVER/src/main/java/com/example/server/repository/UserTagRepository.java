package com.example.server.repository;

import com.example.server.entity.relation.UserTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserTagRepository extends JpaRepository<UserTag,Integer> {
    List<UserTag> getUserTagsByUser_id(int id);

    void deleteUserTagsByUser_Id(int id);

}
