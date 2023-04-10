package com.example.server.controller;

import com.example.server.qr.QrApiResponse;
import com.example.server.qr.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        results.add(new Result("22000556", "홍길동", "202304102033"));
        response.setResults(results);
        return ResponseEntity.ok(response);
    }
}
