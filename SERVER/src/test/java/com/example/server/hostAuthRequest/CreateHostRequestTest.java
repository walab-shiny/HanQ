package com.example.server.hostAuthRequest;

import com.example.server.dto.AcceptHostRequestDto;
import com.example.server.dto.CreateHostRequestDto;
import com.example.server.dto.DeclineHostRequestDto;
import com.example.server.dto.HostAuthRequestDto;
import com.example.server.entity.HostAuthRequest;
import com.example.server.entity.User;
import com.example.server.service.HostAuthRequestService;
import com.example.server.service.UserService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CreateHostRequestTest {
    @Autowired
    HostAuthRequestService hostAuthRequestService;
    @Autowired
    UserService userService;

    @Test
    @Order(1)
    public void createRequests() {
        HostAuthRequest authRequest;
        authRequest = hostAuthRequestService.createAuthRequest(
                new CreateHostRequestDto("전산전자공학부 임원단입니다! 호스트 권한 요청합니다","전산전자공학부임원단",7),"106748212855095490148");
        HostAuthRequestDto dto= authRequest.toDto();
//        assertThat(userService.getUser(authRequest.getUser().getId()).getId()).isEqualTo(1);
        assertThat(authRequest.getContent()).isEqualTo("전산전자공학부 임원단입니다! 호스트 권한 요청합니다");
        assertThat(authRequest.getAffiliation()).isEqualTo("전산전자공학부임원단");
        assertThat(authRequest.getHostUntil()).isEqualTo("2023-05-20");
        System.out.println("dto = " + dto);
    }
    @Test
    @Order(2)
    public void getRequests() {
        List<HostAuthRequestDto> list = hostAuthRequestService.getRequests(1);
        assertThat(list.size()).isEqualTo(2);
        list.forEach(l -> System.out.println("l = " + l.toString()));
    }
    @Test
    @Order(3)
    public void acceptRequest() {
        HostAuthRequestDto dto = hostAuthRequestService.acceptRequest(new AcceptHostRequestDto(1, "2023-03-26",""));
//        assertThat(userService.getUser(dto.getUserId()).getIsHost()).isEqualTo(true);
//        assertThat(userService.getUser(dto.getUserId()).getHostUntil()).isEqualTo("2023-03-26");
    }
    @Test
    @Order(4)
    public void declineRequest() {
        HostAuthRequestDto dto = hostAuthRequestService.declineRequest(new DeclineHostRequestDto(2,"안됩니다!"));
        assertThat(dto.getResponse()).isEqualTo("안됩니다!");
//        assertThat(userService.getUser(dto.getUserId()).getIsHost()).isEqualTo(false);
    }
    @Test
    @Order(5)
    public void getRequests2() {
        List<HostAuthRequestDto> list = hostAuthRequestService.getRequests();
        assertThat(list.size()).isEqualTo(2);
        list.forEach(l -> System.out.println("l = " + l.toString()));
    }
}
