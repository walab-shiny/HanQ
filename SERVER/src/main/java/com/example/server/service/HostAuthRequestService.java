package com.example.server.service;

import com.example.server.dto.AcceptHostRequestDto;
import com.example.server.dto.DeclineHostRequestDto;
import com.example.server.dto.HostAuthRequestDto;
import com.example.server.dto.CreateHostRequestDto;
import com.example.server.entity.HostAuthRequest;
import com.example.server.entity.User;
import com.example.server.repository.HostAuthRequestRepository;
import com.example.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Host;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HostAuthRequestService {
    private final HostAuthRequestRepository hostAuthRequestRepository;
    private final UserRepository userRepository;

    @Transactional
    public HostAuthRequestDto createAuthRequest(CreateHostRequestDto dto) {
        HostAuthRequest request = new HostAuthRequest(dto);
        User user = userRepository.findById(dto.getUserId()).orElseThrow();
        request.setUser(user);
        user.addRequest(request);
        return hostAuthRequestRepository.save(request).toDto();
    }
    // accept 및 decline api 에 대한 보호 생각하기
    @Transactional
    public HostAuthRequestDto acceptRequest(AcceptHostRequestDto dto) {
        HostAuthRequest request = hostAuthRequestRepository.findById(dto.getId()).orElseThrow();
        if(!dto.getDuration().isEmpty()) {
            request.getUser().setHostUntil(dto.getDuration());
        }
        request.accept(request.getUser());
        return request.toDto();
    }
    @Transactional
    public HostAuthRequestDto declineRequest(DeclineHostRequestDto dto) {
        HostAuthRequest request = hostAuthRequestRepository.findById(dto.getId()).orElseThrow();
        request.decline();
        request.writeResponse(dto.getResponse());
        return request.toDto();
    }

    @Transactional
    public HostAuthRequestDto getRequest(int id) {
        return hostAuthRequestRepository.findById(id).orElseThrow().toDto();
    }
    @Transactional
    public List<HostAuthRequestDto> getRequests() {
        return hostAuthRequestRepository.findAll().stream().map(HostAuthRequest::toDto).collect(Collectors.toList());
    }
    @Transactional
    public List<HostAuthRequestDto> getRequests(int status) {
        return hostAuthRequestRepository.findAllByStatus(status).stream().map(HostAuthRequest::toDto).collect(Collectors.toList());
    }


}
