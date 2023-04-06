package com.example.server.repository;


import com.example.server.entity.Tag;
import com.example.server.entity.relation.EventTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventTagRepository extends JpaRepository<EventTag,Integer> {
    public List<EventTag> findEventTagByEvent_Id(int id);
    public void deleteAllByEvent_Id(int id);
}
