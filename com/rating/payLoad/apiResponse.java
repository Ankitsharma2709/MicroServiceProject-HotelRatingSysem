package com.rating.payLoad;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class apiResponse {
    private String message;
    private boolean success;
    private HttpStatus status;
}
