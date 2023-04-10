package com.example.server.service;

import com.example.server.dto.QrResponseDto;
import com.example.server.dto.QrStringDto;
import com.example.server.entity.Attend;
import com.example.server.entity.Event;
import com.example.server.entity.User;
import com.example.server.qr.QrApiResponse;
import com.example.server.qr.Result;
import com.example.server.repository.AttendRepository;
import com.example.server.repository.EventRepository;
import com.example.server.repository.UserRepository;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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

    @Transactional
    public QrResponseDto createAttend(QrStringDto dto) throws Exception {
        Base64.Encoder encoder = Base64.getEncoder();
        String encoded = encoder.encodeToString(dto.getQrString().getBytes());
        Result result = this.getResult(encoded);
        if(attendRepository.existsAttendByUserStudentNumAndEventId(Long.valueOf(result.getUser_number()),dto.getEventId())) {
            QrResponseDto responseDto = new QrResponseDto(result);
            responseDto.setIsDuplicate(true);
            return responseDto;
        }
        Attend attend = new Attend();
        String time = result.getQr_tagging_time();
        SimpleDateFormat fromQr = new SimpleDateFormat("yyyyMMddHHmm");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        String formatted = format.format(fromQr.parse(time));
        attend.setTaggedAt(LocalDateTime.parse(formatted));
        User user;
        if(userRepository.existsUserByStudentNum(Long.valueOf(result.getUser_number()))) {
            user = userRepository.findUserByStudentNum(Long.valueOf(result.getUser_number()));
            attend.setUser(user);
        }
        else {
            user = new User(result);
            attend.setUser(userRepository.save(user));
        }
        Event event = eventRepository.findById(6).orElseThrow();
        attend.setEvent(event);
        attendRepository.save(attend);
        user.addAttend(attend);
        event.addAttend(attend);
        QrResponseDto responseDto = new QrResponseDto(result);
        responseDto.setTaggedAt(formatted);
        return responseDto;
    }
//    @Transactional
//    public QrResponseDto createAttendTest() throws Exception {
//        Result result = getResultTest();
//        if(attendRepository.existsAttendByUserStudentNumAndEventId(Long.valueOf(result.getUser_number()),1)) {
//            QrResponseDto responseDto = new QrResponseDto(result);
//            responseDto.setIsDuplicate(true);
//            return responseDto;
//        }
//        Attend attend = new Attend();
//        String time = result.getQr_tagging_time();
//        SimpleDateFormat fromQr = new SimpleDateFormat("yyyyMMddHHmm");
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
//        String formatted = format.format(fromQr.parse(time));
//        attend.setTaggedAt(LocalDateTime.parse(formatted));
//        User user;
//        if(userRepository.existsUserByStudentNum(Long.valueOf(result.getUser_number()))) {
//            user = userRepository.findUserByStudentNum(Long.valueOf(result.getUser_number()));
//            attend.setUser(user);
//        }
//        else {
//            user = new User(result);
//            attend.setUser(userRepository.save(user));
//        }
//        Event event = eventRepository.findById(1).orElseThrow();
//        attend.setEvent(event);
//        attendRepository.save(attend);
//        user.addAttend(attend);
//        event.addAttend(attend);
//        QrResponseDto dto = new QrResponseDto(result);
//        dto.setTaggedAt(formatted);
//        return dto;
//    }
    public QrApiResponse getQrResponse(String encoded) throws Exception {
        String url = apiUrl + encoded;
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
        return response.getResults().get(0);
    }
    public List<Attend> getAttendsByUserId(int id) {
        return attendRepository.getAttendsByUserId(id);
    }
//    public QrApiResponse getQrResponseTest() throws Exception {
//        String url = "http://localhost:8080/api/test";
//        HttpRequest getRequest = HttpRequest.newBuilder()
//                .uri(new URI(url))
//                .build();
//        HttpClient client = HttpClient.newHttpClient();
//        HttpResponse<String> getResponse = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
//        Gson gson = new Gson();
//        return gson.fromJson(getResponse.body(), QrApiResponse.class);
//    }
//    public Result getResultTest() throws Exception {
//        QrApiResponse response = getQrResponseTest();
//        return response.getResults().get(0);
//    }

}
