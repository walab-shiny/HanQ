package com.example.server.service;

import com.example.server.controller.TestController;
import com.example.server.dto.*;
import com.example.server.entity.Attend;
import com.example.server.entity.Event;
import com.example.server.entity.User;
import com.example.server.qr.QrApiResponse;
import com.example.server.qr.Result;
import com.example.server.repository.AttendRepository;
import com.example.server.repository.DepartmentRepository;
import com.example.server.repository.EventRepository;
import com.example.server.repository.UserRepository;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendService {
    private final AttendRepository attendRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    @Value("${API_KEY}")
    private String apiUrl;
    private final DepartmentRepository departmentRepository;

    @Transactional
    public QrResponseDto createAttend(QrStringDto dto) throws Exception {
        Base64.Encoder encoder = Base64.getEncoder();
        String encoded = encoder.encodeToString(dto.getQrString().getBytes());
        Result result = getResult(encoded);
        if(attendRepository.existsAttendByUserStudentNumAndEventId(Long.valueOf(result.getUser_number()),dto.getEventId())) {
            QrResponseDto responseDto = new QrResponseDto(result);
            responseDto.setIsDuplicate(true);
            responseDto.setTotal(attendRepository.countDistinctByEventId(dto.getEventId()));
            return responseDto;
        }
        Attend attend = new Attend();
        attend.setTaggedAt(LocalDateTime.now(ZoneId.of("Asia/Seoul")));
        User user;
        if(userRepository.existsUserByStudentNum(Long.valueOf(result.getUser_number()))) {
            user = userRepository.findUserByStudentNum(Long.valueOf(result.getUser_number()));
            attend.setUser(user);
            if(user.getDepartment()==null) {
                user.setDepartment(departmentRepository.findDepartmentByName(result.getDept_name()).orElse(null));
            }
            if(user.getMajor1().isEmpty() || user.getMajor2().isEmpty()) {
                user.setMajor(result.getMajor1_name(), result.getMajor2_name());
            }
        }
        else {
            user = new User(result);
            user.setDepartment(departmentRepository.findDepartmentByName(result.getDept_name()).orElse(null));
            attend.setUser(userRepository.save(user));
        }
        Event event = eventRepository.findById(dto.getEventId()).orElseThrow();
        attend.setEvent(event);
        attendRepository.save(attend);
        user.addAttend(attend);
        event.addAttend(attend);
        QrResponseDto responseDto = new QrResponseDto(result);
        responseDto.setTaggedAt(LocalDateTime.now(ZoneId.of("Asia/Seoul")).toString());
        responseDto.setTotal(attendRepository.countDistinctByEventId(dto.getEventId()));
        return responseDto;
    }
    public QrApiResponse getQrResponse(String encoded) throws Exception {
        String url = apiUrl + encoded;
        System.out.println("url = " + url);
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI(url))
                .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> getResponse = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
        Gson gson = new Gson();
        return gson.fromJson(getResponse.body(), QrApiResponse.class);
    }
    public Result getResult(String encoded) throws Exception {
        QrApiResponse response = getQrResponse(encoded);
        return response.getResult().get(0);
    }
    public Result getInformation(String qrString) throws Exception {
        Base64.Encoder encoder = Base64.getEncoder();
        String encoded = encoder.encodeToString(qrString.getBytes());
        return getQrResponse(encoded).getResult().get(0);
    }
    public List<Attend> getAttendsByUserId(int id) {
        return attendRepository.getAttendsByUserId(id);
    }
    @Transactional
    public AttendUserDto addMemo(MemoCreateDto dto, String token) {
        User host = userRepository.findUserByToken(token);
        if(host.getIsHost()) {
            Attend attend = attendRepository.getAttendByUser_StudentNumAndEventId(dto.getStudentNum(), dto.getEventId());
            attend.setMemo(dto.getMemo());
            return attend.toAttendUserDto();
        }
        return null;
    }
    @Transactional
    public AttendUserDto deleteMemo(MemoDeleteDto dto, String token) {
        User host = userRepository.findUserByToken(token);
        if(host.getIsHost()) {
            Attend attend = attendRepository.getAttendByUser_StudentNumAndEventId(dto.getStudentNum(), dto.getEventId());
            attend.setMemo("");
            return attend.toAttendUserDto();
        }
        return null;
    }
    public AttendMonthlyCountResponse countAttendMonthly(AttendMonthlyCountRequest request) {
        LocalDateTime now = LocalDateTime.parse(request.getDate());
        List<Attend> list = attendRepository.getAttendByTaggedAtBetween(now.withDayOfMonth(1), now.withDayOfMonth(1).plusMonths(1));
        return new AttendMonthlyCountResponse(list.size());
    }

    public AttendCountDto countTotalAttend() {
        return new AttendCountDto(attendRepository.findAll().size());
    }

    @Transactional
    public List<AttendUserDto> findAttendUsersByPage(int id, Pageable pageable) {
        return attendRepository.getDistinctByEventId(id,pageable).map(Attend::toAttendUserDto).getContent();
    }
//    @Transactional
//    public QrResponseDto testDept() throws Exception {
//        String url = "http://localhost:8080/api/test";
//        HttpRequest getRequest = HttpRequest.newBuilder()
//                .uri(new URI(url))
//                .build();
//        HttpClient client = HttpClient.newHttpClient();
//        HttpResponse<String> getResponse = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
//        Gson gson = new Gson();
//        QrApiResponse res = gson.fromJson(getResponse.body(), QrApiResponse.class);
//        Result result = res.getResult().get(0);
//        int eventId = 4;
//        if(attendRepository.existsAttendByUserStudentNumAndEventId(Long.valueOf(result.getUser_number()),eventId)) {
//            QrResponseDto responseDto = new QrResponseDto(result);
//            responseDto.setIsDuplicate(true);
//            return responseDto;
//        }
//        Attend attend = new Attend();
//        attend.setTaggedAt(LocalDateTime.now(ZoneId.of("Asia/Seoul")));
//        User user;
//        if(userRepository.existsUserByStudentNum(Long.valueOf(result.getUser_number()))) {
//            user = userRepository.findUserByStudentNum(Long.valueOf(result.getUser_number()));
//            attend.setUser(user);
//            if(user.getDepartment()==null) {
//                user.setDepartment(departmentRepository.findDepartmentByName(result.getDept_name()).orElse(null));
//            }
//            if(user.getMajor1().isEmpty() || user.getMajor2().isEmpty()) {
//                user.setMajor(result.getMajor1_name(), result.getMajor2_name());
//            }
//        }
//        else {
//            user = new User(result);
//            user.setDepartment(departmentRepository.findDepartmentByName(result.getDept_name()).orElse(null));
//            attend.setUser(userRepository.save(user));
//        }
//        Event event = eventRepository.findById(eventId).orElseThrow();
//        attend.setEvent(event);
//        attendRepository.save(attend);
//        user.addAttend(attend);
//        event.addAttend(attend);
//        QrResponseDto responseDto = new QrResponseDto(result);
//        responseDto.setTaggedAt(LocalDateTime.now(ZoneId.of("Asia/Seoul")).toString());
//        responseDto.setTotal(attendRepository.countDistinctByEventId(eventId));
//        return responseDto;
//    }
}
