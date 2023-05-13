package com.example.server.user;

import com.example.server.dto.UserDto;
import com.example.server.dto.UserUpdateDto;
import com.example.server.entity.Tag;
import com.example.server.service.TagService;
import com.example.server.service.UserService;
import com.example.server.service.UserTagService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserUpdateTest {
    @Autowired
    UserService userService;
    @Autowired
    TagService tagService;
    @Autowired
    UserTagService userTagService;

    @Test
    @DisplayName("유저 업데이트 테스트")
    public void updateTest() {
        List<Integer> tags = new ArrayList<>();
        tags.add(1);
        tags.add(2);
        List<Integer> empty = new ArrayList<>();
        List<Tag> tagList = tagService.getTagsFromList(tags);
        UserDto dto = userService.updateUser(new UserUpdateDto(tags, "test"), "83247847347");
        UserDto emptyDto = userService.updateUser(new UserUpdateDto(empty, "test"), "112162645752275142872");
//        assertThat(dto.getTags().isEmpty()).isEqualTo(false);
//        assertThat(dto.getTags().get(0).getId()).isEqualTo(tagList.get(0).toDto().getId());
//        assertThat(emptyDto.getTags().isEmpty()).isEqualTo(true);
        System.out.println("dto = " + dto);
        System.out.println("emptyDto = " + emptyDto);
    }

    @Test
    @DisplayName("유저 관계 삭제 테스트")
    public void deleteRelationsTest() {
        userTagService.deleteRelations(1);
    }
}
