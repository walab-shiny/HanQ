package com.example.server.repository;


import com.example.server.entity.Tag;
import com.example.server.entity.relation.EventTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventTagRepository extends JpaRepository<EventTag,Integer> {
    List<EventTag> findEventTagByEvent_Id(int id);
    List<EventTag> findEventTagsByTag_Id(int id);
    void deleteAllByEvent_Id(int id);
}
