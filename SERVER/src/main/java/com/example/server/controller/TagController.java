package com.example.server.controller;

import com.example.server.dto.TagCreateDto;
import com.example.server.dto.TagDeleteDto;
import com.example.server.dto.TagDto;
import com.example.server.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/tag")
public class TagController {
    private final TagService tagService;

    @PostMapping
    public ResponseEntity<TagDto> createTag(@RequestBody TagCreateDto dto) {
        return ResponseEntity.ok(tagService.createTag(dto));
    }
    @GetMapping
    public ResponseEntity<List<TagDto>> getTags() {
        return ResponseEntity.ok(tagService.getTags());
    }
    @PostMapping("/delete")
    public ResponseEntity<TagDto> deleteTag(@RequestBody TagDeleteDto dto) {
        return ResponseEntity.ok(tagService.deleteTag(dto));
    }


}
