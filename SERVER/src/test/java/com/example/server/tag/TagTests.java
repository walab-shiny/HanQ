package com.example.server.tag;

import com.example.server.dto.TagCreateDto;
import com.example.server.dto.TagDeleteDto;
import com.example.server.dto.TagDto;
import com.example.server.service.TagService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TagTests {
    @Autowired
    TagService tagService;

    @Test
    @Order(1)
    public void create() {
        TagCreateDto dto = new TagCreateDto("전산전자공학부");
        TagDto created = tagService.createTag(dto);
        TagCreateDto dto2 = new TagCreateDto("경영경제학부");
        TagDto created2 = tagService.createTag(dto2);
        TagCreateDto dto3 = new TagCreateDto("체육대회");
        TagDto created3 = tagService.createTag(dto3);
        assertThat(created.getId()).isEqualTo(1);
        assertThat(created.getName()).isEqualTo("전산전자공학부");
        assertThat(created2.getId()).isEqualTo(2);
        assertThat(created2.getName()).isEqualTo("경영경제학부");
        assertThat(created3.getId()).isEqualTo(3);
        assertThat(created3.getName()).isEqualTo("체육대회");
    }
    @Test
    @Order(2)
    public void list() {
        List<TagDto> list = tagService.getTags();
        list.forEach(t-> System.out.println("t = " + t));
        assertThat(list.size()).isEqualTo(3);
    }
    @Test
    @Order(3)
    public void delete() {
        TagDto deleted = tagService.deleteTag(new TagDeleteDto(1));
        assertThat(deleted.getId()).isEqualTo(1);
        assertThat(deleted.getName()).isEqualTo("전산전자공학부");
    }
    @Test
    @Order(4)
    public void list2() {
        List<TagDto> list = tagService.getTags();
        list.forEach(t -> System.out.println("t = " + t));
        assertThat(list.size()).isEqualTo(2);
    }
}
