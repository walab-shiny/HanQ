package com.example.server.service;

import com.example.server.dto.TagCreateDto;
import com.example.server.dto.TagDeleteDto;
import com.example.server.dto.TagDto;
import com.example.server.entity.Tag;
import com.example.server.entity.relation.EventTag;
import com.example.server.repository.TagRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    @Transactional
    public TagDto createTag(TagCreateDto dto) {
        Tag tag = new Tag(dto);
        return tagRepository.save(tag).toDto();
    }
    @Transactional
    public TagDto deleteTag(TagDeleteDto dto) {
        Tag deleted = tagRepository.findById(dto.getId()).orElseThrow(null);
        tagRepository.deleteById(dto.getId());
        return deleted.toDto();
    }
    @Transactional
    public List<TagDto> getTags() {
        List<Tag> tags = tagRepository.findAll();
        return tags.stream().map(Tag::toDto).collect(Collectors.toList());
    }
    @Transactional
    public List<Tag> getTagsFromEvent(List<Integer> ids) {
        return tagRepository.findAllById(ids);
    }
    @Transactional
    public List<Tag> getTagsFromEventTag(List<EventTag> list) {
        return list.stream().map(EventTag::getTag).collect(Collectors.toList());
    }
    @Transactional
    public TagDto getTag(int id) {
        return tagRepository.findById(id).orElseThrow(null).toDto();
    }

}
