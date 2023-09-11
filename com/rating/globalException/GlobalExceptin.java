package com.rating.globalException;

import com.rating.customException.ResourceNotFoundException;
import com.rating.payLoad.apiResponse;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptin{
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<apiResponse> handleResourceNotFoundException(ResourceNotFoundException ex){
        String message =    ex.getMessage();
        apiResponse response = apiResponse.builder()
                .message(message)
                .success(true)
                .status(HttpStatus.BAD_REQUEST)
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
