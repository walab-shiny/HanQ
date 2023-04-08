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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class AttendService {
    private final AttendRepository attendRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    @Transactional
    public QrResponseDto createAttend(QrStringDto dto) throws Exception {
        Base64.Encoder encoder = Base64.getEncoder();
        String encoded = encoder.encodeToString(dto.getQrString().getBytes());
        String url = "https://hisnet.handong.edu/api/hdOAC/index.php/user/qrcode?qrReturnTxt=" + encoded;
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI(url))
                .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> getResponse = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
        Gson gson = new Gson();
        QrApiResponse response = gson.fromJson(getResponse.body(), QrApiResponse.class);
        Result result = response.getResult();
        if(attendRepository.existsAttendByUserStudentNumAndEventId(Long.valueOf(result.getUser_number()),dto.getEventId())) {
            QrResponseDto responseDto = new QrResponseDto(result);
            responseDto.setIsDuplicate(true);
            return responseDto;
        }
        Attend attend = new Attend();
        String time = result.getQr_tagging_time();
        StringBuilder sb = new StringBuilder(time);
        sb.insert(8,'T');
        attend.setTaggedAt(LocalDateTime.parse(sb));
        User user;
        if(userRepository.existsUserByStudentNum(Long.valueOf(result.getUser_number()))) {
            user = userRepository.findUserByStudentNum(Long.valueOf(result.getUser_number()));
            attend.setUser(user);
        }
        else {
            user = new User(result);
            attend.setUser(userRepository.save(user));
        }
        Event event = eventRepository.findById(dto.getEventId()).orElseThrow();
        attend.setEvent(event);
        attendRepository.save(attend);
        user.addAttend(attend);
        event.addAttend(attend);
        return new QrResponseDto(result);
    }
}
