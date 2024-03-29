package com.example.server.controller;

import com.example.server.qr.QrApiResponse;
import com.example.server.qr.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/test")
public class TestController {
    @GetMapping
    public ResponseEntity<QrApiResponse> test() {
        QrApiResponse response = new QrApiResponse();
        response.setStatus(200);
        response.setDescription("Request Success");
        List<Result> results = new ArrayList<>();
        results.add(new Result("22000328", "배주영", "상담심리사회복지학부","상담심리학과","사회복지학과","202304110154"));
        response.setResult(results);
        return ResponseEntity.ok(response);
    }
}
