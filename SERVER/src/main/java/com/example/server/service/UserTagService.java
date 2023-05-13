package com.example.server.service;

import com.example.server.entity.Tag;
import com.example.server.entity.User;
import com.example.server.entity.relation.UserTag;
import com.example.server.repository.TagRepository;
import com.example.server.repository.UserRepository;
import com.example.server.repository.UserTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserTagService {
    private final UserRepository userRepository;
    private final TagRepository tagRepository;
    private final TagService tagService;
    private final UserTagRepository userTagRepository;

    @Transactional
    public List<UserTag> createRelations(List<Tag> tags, User user) {
        tags.forEach(t -> {
            UserTag userTag = new UserTag();
            userTag.setUser(user);
            userTag.setTag(t);
            UserTag saved = userTagRepository.save(userTag);
            user.getTags().add(saved);
            t.getUsers().add(saved);
        });
        return userTagRepository.getUserTagsByUser_id(user.getId());
    }
    @Transactional
    public void deleteRelations(int id) {
        userTagRepository.deleteUserTagsByUser_Id(id);
    }
}
