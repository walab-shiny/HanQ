package com.example.server.qr;

import com.example.server.entity.Attend;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QrApiResponse {
    private int status;
    private String description;
    private List<Result> results;

    public Result getResult() {
        return this.results.get(0);
    }
}
