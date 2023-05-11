package com.example.server.repository;

import com.example.server.entity.Tag;
import com.example.server.entity.relation.EventTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TagRepository extends JpaRepository<Tag,Integer> {
}
