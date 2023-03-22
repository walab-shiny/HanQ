package com.example.server.hostAuthRequest;

import com.example.server.dto.CreateHostRequestDto;
import com.example.server.dto.HostAuthRequestDto;
import com.example.server.service.HostAuthRequestService;
import com.example.server.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CreateHostRequestTest {
    @Autowired
    HostAuthRequestService hostAuthRequestService;
    @Autowired
    UserService userService;

    @Test
    public void createRequest() {
        HostAuthRequestDto authRequest = hostAuthRequestService.createAuthRequest(
                new CreateHostRequestDto(1, "전산전자공학부 임원단입니다! 호스트 권한 요청합니다"));
        assertThat(Objects.requireNonNull(userService.getUser(authRequest.getUserId()).getBody()).getId()).isEqualTo(1);
        assertThat(authRequest.getContent()).isEqualTo("전산전자공학부 임원단입니다! 호스트 권한 요청합니다");
    }

}
