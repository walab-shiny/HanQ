package com.example.server.qr;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QrApiResponse {
    private int status;
    private String description;
    private List<Result> result;
}
