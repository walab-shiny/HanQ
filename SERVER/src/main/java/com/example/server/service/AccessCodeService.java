package com.example.server.service;

import com.example.server.dto.AccessCodeDto;
import com.example.server.entity.AccessCode;
import com.example.server.entity.Event;
import com.example.server.repository.AccessCodeRepository;
import com.example.server.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccessCodeService {
    private final AccessCodeRepository accessCodeRepository;
    private final EventRepository eventRepository;

    // accessCode creation
    @Transactional
    public void createAccessCode(int eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow();
        // accessCode 생성하는 로직;
        String randomStr = RandomCodeGenerator.getBase62(6);
        while(accessCodeRepository.existsAccessCodeByCode(randomStr))
            randomStr = RandomCodeGenerator.getBase62(6);
        //저장
        AccessCode code = new AccessCode(randomStr);
        AccessCode saved = accessCodeRepository.save(code);
        //관계 설정
        saved.setEvent(event);
        event.setAccessCode(saved);
    }


    public static class RandomCodeGenerator {
        private static char[] _base62chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
        private static Random _random = new Random();

        public static String getBase62(int length) {
            StringBuilder sb = new StringBuilder(length);
            for(int i=0;i<length;i++)
                sb.append(_base62chars[_random.nextInt(62)]);
            return sb.toString();
        }
    }
}
